// Autogenerated by Thrift Compiler (1.0.0-dev)
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING

package event

import (
	"bytes"
	"fmt"
	"git.apache.org/thrift.git/lib/go/thrift"
	"github.com/Workiva/frugal/example/go/gen-go/base"
)

// (needed to ensure safety because of naive import list construction.)
var _ = thrift.ZERO
var _ = fmt.Printf
var _ = bytes.Equal

var _ = base.GoUnusedProtection__
var GoUnusedProtection__ int

type ID int64

func IDPtr(v ID) *ID { return &v }

type Int int32

func IntPtr(v Int) *Int { return &v }

type Request map[Int]string

func RequestPtr(v Request) *Request { return &v }

// This docstring gets added to the generated code because it has
// the @ sign.
//
// Attributes:
//  - ID: ID is a unique identifier for an event.
//  - Message: Message contains the event payload.
type Event struct {
	ID      ID     `thrift:"ID,1,required" db:"ID" json:"ID"`
	Message string `thrift:"Message,2,required" db:"Message" json:"Message"`
}

func NewEvent() *Event {
	return &Event{
		ID: -1,
	}
}

func (p *Event) GetID() ID {
	return p.ID
}

func (p *Event) GetMessage() string {
	return p.Message
}
func (p *Event) Read(iprot thrift.TProtocol) error {
	if _, err := iprot.ReadStructBegin(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T read error: ", p), err)
	}

	var issetID bool = false
	var issetMessage bool = false

	for {
		_, fieldTypeId, fieldId, err := iprot.ReadFieldBegin()
		if err != nil {
			return thrift.PrependError(fmt.Sprintf("%T field %d read error: ", p, fieldId), err)
		}
		if fieldTypeId == thrift.STOP {
			break
		}
		switch fieldId {
		case 1:
			if err := p.ReadField1(iprot); err != nil {
				return err
			}
			issetID = true
		case 2:
			if err := p.ReadField2(iprot); err != nil {
				return err
			}
			issetMessage = true
		default:
			if err := iprot.Skip(fieldTypeId); err != nil {
				return err
			}
		}
		if err := iprot.ReadFieldEnd(); err != nil {
			return err
		}
	}
	if err := iprot.ReadStructEnd(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T read struct end error: ", p), err)
	}
	if !issetID {
		return thrift.NewTProtocolExceptionWithType(thrift.INVALID_DATA, fmt.Errorf("Required field ID is not set"))
	}
	if !issetMessage {
		return thrift.NewTProtocolExceptionWithType(thrift.INVALID_DATA, fmt.Errorf("Required field Message is not set"))
	}
	return nil
}

func (p *Event) ReadField1(iprot thrift.TProtocol) error {
	if v, err := iprot.ReadI64(); err != nil {
		return thrift.PrependError("error reading field 1: ", err)
	} else {
		temp := ID(v)
		p.ID = temp
	}
	return nil
}

func (p *Event) ReadField2(iprot thrift.TProtocol) error {
	if v, err := iprot.ReadString(); err != nil {
		return thrift.PrependError("error reading field 2: ", err)
	} else {
		p.Message = v
	}
	return nil
}

func (p *Event) Write(oprot thrift.TProtocol) error {
	if err := oprot.WriteStructBegin("Event"); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write struct begin error: ", p), err)
	}
	if err := p.writeField1(oprot); err != nil {
		return err
	}
	if err := p.writeField2(oprot); err != nil {
		return err
	}
	if err := oprot.WriteFieldStop(); err != nil {
		return thrift.PrependError("write field stop error: ", err)
	}
	if err := oprot.WriteStructEnd(); err != nil {
		return thrift.PrependError("write struct stop error: ", err)
	}
	return nil
}

func (p *Event) writeField1(oprot thrift.TProtocol) (err error) {
	if err := oprot.WriteFieldBegin("ID", thrift.I64, 1); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write field begin error 1:ID: ", p), err)
	}
	if err := oprot.WriteI64(int64(p.ID)); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T.ID (1) field write error: ", p), err)
	}
	if err := oprot.WriteFieldEnd(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write field end error 1:ID: ", p), err)
	}
	return err
}

func (p *Event) writeField2(oprot thrift.TProtocol) (err error) {
	if err := oprot.WriteFieldBegin("Message", thrift.STRING, 2); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write field begin error 2:Message: ", p), err)
	}
	if err := oprot.WriteString(string(p.Message)); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T.Message (2) field write error: ", p), err)
	}
	if err := oprot.WriteFieldEnd(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write field end error 2:Message: ", p), err)
	}
	return err
}

func (p *Event) String() string {
	if p == nil {
		return "<nil>"
	}
	return fmt.Sprintf("Event(%+v)", *p)
}

// Attributes:
//  - ID: ID is a unique identifier for an awesome exception.
//  - Reason: Reason contains the error message.
type AwesomeException struct {
	ID     ID     `thrift:"ID,1,required" db:"ID" json:"ID"`
	Reason string `thrift:"Reason,2,required" db:"Reason" json:"Reason"`
}

func NewAwesomeException() *AwesomeException {
	return &AwesomeException{}
}

func (p *AwesomeException) GetID() ID {
	return p.ID
}

func (p *AwesomeException) GetReason() string {
	return p.Reason
}
func (p *AwesomeException) Read(iprot thrift.TProtocol) error {
	if _, err := iprot.ReadStructBegin(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T read error: ", p), err)
	}

	var issetID bool = false
	var issetReason bool = false

	for {
		_, fieldTypeId, fieldId, err := iprot.ReadFieldBegin()
		if err != nil {
			return thrift.PrependError(fmt.Sprintf("%T field %d read error: ", p, fieldId), err)
		}
		if fieldTypeId == thrift.STOP {
			break
		}
		switch fieldId {
		case 1:
			if err := p.ReadField1(iprot); err != nil {
				return err
			}
			issetID = true
		case 2:
			if err := p.ReadField2(iprot); err != nil {
				return err
			}
			issetReason = true
		default:
			if err := iprot.Skip(fieldTypeId); err != nil {
				return err
			}
		}
		if err := iprot.ReadFieldEnd(); err != nil {
			return err
		}
	}
	if err := iprot.ReadStructEnd(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T read struct end error: ", p), err)
	}
	if !issetID {
		return thrift.NewTProtocolExceptionWithType(thrift.INVALID_DATA, fmt.Errorf("Required field ID is not set"))
	}
	if !issetReason {
		return thrift.NewTProtocolExceptionWithType(thrift.INVALID_DATA, fmt.Errorf("Required field Reason is not set"))
	}
	return nil
}

func (p *AwesomeException) ReadField1(iprot thrift.TProtocol) error {
	if v, err := iprot.ReadI64(); err != nil {
		return thrift.PrependError("error reading field 1: ", err)
	} else {
		temp := ID(v)
		p.ID = temp
	}
	return nil
}

func (p *AwesomeException) ReadField2(iprot thrift.TProtocol) error {
	if v, err := iprot.ReadString(); err != nil {
		return thrift.PrependError("error reading field 2: ", err)
	} else {
		p.Reason = v
	}
	return nil
}

func (p *AwesomeException) Write(oprot thrift.TProtocol) error {
	if err := oprot.WriteStructBegin("AwesomeException"); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write struct begin error: ", p), err)
	}
	if err := p.writeField1(oprot); err != nil {
		return err
	}
	if err := p.writeField2(oprot); err != nil {
		return err
	}
	if err := oprot.WriteFieldStop(); err != nil {
		return thrift.PrependError("write field stop error: ", err)
	}
	if err := oprot.WriteStructEnd(); err != nil {
		return thrift.PrependError("write struct stop error: ", err)
	}
	return nil
}

func (p *AwesomeException) writeField1(oprot thrift.TProtocol) (err error) {
	if err := oprot.WriteFieldBegin("ID", thrift.I64, 1); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write field begin error 1:ID: ", p), err)
	}
	if err := oprot.WriteI64(int64(p.ID)); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T.ID (1) field write error: ", p), err)
	}
	if err := oprot.WriteFieldEnd(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write field end error 1:ID: ", p), err)
	}
	return err
}

func (p *AwesomeException) writeField2(oprot thrift.TProtocol) (err error) {
	if err := oprot.WriteFieldBegin("Reason", thrift.STRING, 2); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write field begin error 2:Reason: ", p), err)
	}
	if err := oprot.WriteString(string(p.Reason)); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T.Reason (2) field write error: ", p), err)
	}
	if err := oprot.WriteFieldEnd(); err != nil {
		return thrift.PrependError(fmt.Sprintf("%T write field end error 2:Reason: ", p), err)
	}
	return err
}

func (p *AwesomeException) String() string {
	if p == nil {
		return "<nil>"
	}
	return fmt.Sprintf("AwesomeException(%+v)", *p)
}

func (p *AwesomeException) Error() string {
	return p.String()
}
