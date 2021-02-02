module github.com/Workiva/frugal/test/integration/go

go 1.15

require (
	github.com/Workiva/frugal/lib/go v0.0.0
	github.com/apache/thrift v0.13.0
	github.com/go-stomp/stomp v2.1.2+incompatible
	github.com/nats-io/nats.go v1.10.0
	github.com/sirupsen/logrus v1.7.0
)

replace github.com/Workiva/frugal/lib/go v0.0.0 => ../../../lib/go