package ru.spbau.bachelor2015.veselov.hw11

import io.grpc.stub.StreamObserver

class MessengerService : MessengerGrpc.MessengerImplBase() {
    override fun sendMessage(
        request: Protocol.Message,
        responseObserver: StreamObserver<Protocol.Message>
    ) {
        responseObserver.onNext(
            Protocol.Message.newBuilder()
                            .setBody(request.body.reversed())
                            .build()
        )

        responseObserver.onCompleted()
    }
}
