# GRPC with spring boot

This is a demo for using grpc with spring boot and webflux

It has three components:

- proto-module: where it is the proto file with the proto definition
- server-api-aggregator: the HTTP REST service running with webflux listening on 8083
- validator-service: the GRPC server listening on 8081
