package frugal

import (
	"bytes"
	"fmt"
	"io"
	"sync"

	"git.apache.org/thrift.git/lib/go/thrift"
	"github.com/nats-io/nats"
)

var ErrTooLarge = thrift.NewTTransportException(
	thrift.UNKNOWN_TRANSPORT_EXCEPTION, "Message is too large")

// FNatsScopeTransportFactory creates FNatsScopeTransports.
type FNatsScopeTransportFactory struct {
	conn *nats.Conn
}

func NewFNatsScopeTransportFactory(conn *nats.Conn) *FNatsScopeTransportFactory {
	return &FNatsScopeTransportFactory{conn}
}

// GetTransport creates a new NATS FScopeTransport.
func (n *FNatsScopeTransportFactory) GetTransport() FScopeTransport {
	return NewNatsFScopeTransport(n.conn)
}

// fNatsScopeTransport implements FScopeTransport.
type fNatsScopeTransport struct {
	conn        *nats.Conn
	subject     string
	reader      *io.PipeReader
	writer      *io.PipeWriter
	writeBuffer *bytes.Buffer
	sub         *nats.Subscription
	pull        bool
	topicMu     sync.Mutex
	readWriteMu sync.RWMutex
	isOpen      bool
}

// NewNatsFScopeTransport creates a new FScopeTransport which is used for
// pub/sub.
func NewNatsFScopeTransport(conn *nats.Conn) FScopeTransport {
	return &fNatsScopeTransport{conn: conn}
}

// LockTopic sets the publish topic and locks the transport for exclusive
// access.
func (n *fNatsScopeTransport) LockTopic(topic string) error {
	if n.pull {
		return thrift.NewTTransportException(thrift.UNKNOWN_TRANSPORT_EXCEPTION,
			"subscriber cannot lock topic")
	}
	n.topicMu.Lock()
	n.subject = topic
	return nil
}

// UnlockTopic unsets the publish topic and unlocks the transport.
func (n *fNatsScopeTransport) UnlockTopic() error {
	if n.pull {
		return thrift.NewTTransportException(thrift.UNKNOWN_TRANSPORT_EXCEPTION,
			"subscriber cannot unlock topic")
	}
	n.subject = ""
	n.topicMu.Unlock()
	return nil
}

// Subscribe sets the subscribe topic and opens the transport.
func (n *fNatsScopeTransport) Subscribe(topic string) error {
	n.pull = true
	n.subject = topic
	return n.Open()
}

// Open initializes the transport based on whether it's a publisher or
// subscriber. If Open is called before Subscribe, the transport is assumed to
// be a publisher.
func (n *fNatsScopeTransport) Open() error {
	if n.conn.Status() != nats.CONNECTED {
		return thrift.NewTTransportException(thrift.NOT_OPEN,
			fmt.Sprintf("NATS not connected, has status %d", n.conn.Status()))
	}

	if !n.pull {
		n.writeBuffer = bytes.NewBuffer(make([]byte, 0, natsMaxMessageSize))
		return nil
	}

	if n.subject == "" {
		return thrift.NewTTransportException(thrift.UNKNOWN_TRANSPORT_EXCEPTION,
			"cannot subscribe to empty subject")
	}

	n.reader, n.writer = io.Pipe()

	sub, err := n.conn.Subscribe(n.subject, func(msg *nats.Msg) {
		n.writer.Write(msg.Data)
	})
	if err != nil {
		return thrift.NewTTransportExceptionFromError(err)
	}
	n.sub = sub
	n.readWriteMu.Lock()
	n.isOpen = true
	n.readWriteMu.Unlock()
	return nil
}

func (n *fNatsScopeTransport) IsOpen() bool {
	n.readWriteMu.RLock()
	defer n.readWriteMu.RUnlock()
	if n.conn.Status() != nats.CONNECTED || !n.isOpen {
		return false
	}
	if n.pull {
		return n.sub != nil
	}
	return n.writeBuffer != nil
}

// Close unsubscribes in the case of a subscriber and clears the buffer in the
// case of a publisher.
func (n *fNatsScopeTransport) Close() error {
	if !n.IsOpen() || !n.pull {
		return nil
	}
	if err := n.sub.Unsubscribe(); err != nil {
		return thrift.NewTTransportExceptionFromError(err)
	}
	n.sub = nil
	err := n.writer.Close()
	n.writer = nil
	n.readWriteMu.Lock()
	n.isOpen = false
	n.readWriteMu.Unlock()
	return thrift.NewTTransportExceptionFromError(err)
}

func (n *fNatsScopeTransport) Read(p []byte) (int, error) {
	if !n.isOpen() {
		return thrift.NewTTransportException(thrift.END_OF_FILE, "")
	}
	num, err := n.reader.Read(p)
	return num, thrift.NewTTransportExceptionFromError(err)
}

// Write bytes to publish. If buffered bytes exceeds 1MB, ErrTooLarge is
// returned.
func (n *fNatsScopeTransport) Write(p []byte) (int, error) {
	if !n.isOpen() {
		return thrift.NewTTransportException(thrift.NOT_OPEN, "NATS transport not open")
	}
	if len(p)+n.writeBuffer.Len() > natsMaxMessageSize {
		n.writeBuffer.Reset() // Clear any existing bytes.
		return 0, ErrTooLarge
	}
	num, err := n.writeBuffer.Write(p)
	return num, thrift.NewTTransportExceptionFromError(err)
}

// Flush publishes the buffered messages.
func (n *fNatsScopeTransport) Flush() error {
	if !n.isOpen() {
		return thrift.NewTTransportException(thrift.NOT_OPEN, "NATS transport not open")
	}
	data := n.writeBuffer.Bytes()
	if len(data) == 0 {
		return nil
	}
	err := n.conn.Publish(n.subject, data)
	n.writeBuffer.Reset()
	return thrift.NewTTransportExceptionFromError(err)
}

func (n *fNatsScopeTransport) RemainingBytes() uint64 {
	return ^uint64(0) // We don't know unless framed is used.
}
