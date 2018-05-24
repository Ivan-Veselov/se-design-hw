package ru.spbau.bachelor2015.veselov.hw11

import io.grpc.stub.StreamObserver

class MessengerService : MessengerGrpc.MessengerImplBase() {
    override fun sendMessage(
        request: Protocol.Message,
        responseObserver: StreamObserver<Protocol.None>
    ) {
        println(request.body)

        responseObserver.onNext(Protocol.None.newBuilder().build())
        responseObserver.onCompleted()
    }
}
