package ru.spbau.bachelor2015.veselov.hw11

import com.google.protobuf.Timestamp
import io.grpc.ManagedChannelBuilder
import io.grpc.ServerBuilder
import io.grpc.StatusRuntimeException
import io.grpc.stub.StreamObserver
import java.util.concurrent.TimeUnit

class Messenger(
    private val userName: String,
    port: Int,
    peerAddress: String,
    peerPort: Int,
    messageHandler: (Protocol.Message) -> Unit
) {
    private val blockingStub: MessengerGrpc.MessengerBlockingStub

    private val server =
        ServerBuilder.forPort(port)
                     .addService(MessengerService(messageHandler))
                     .build()
                     .start()

    init {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                server.shutdown()
            }
        })

        val channel = ManagedChannelBuilder.forAddress(peerAddress, peerPort)
                                           .usePlaintext()
                                           .build()

        blockingStub = MessengerGrpc.newBlockingStub(channel)
    }

    fun sendMessage(body: String): Boolean {
        val message = Protocol.Message.newBuilder()
                                      .setUserName(userName)
                                      .setTimestamp(
                                          Timestamp.newBuilder().setSeconds(
                                              TimeUnit.MILLISECONDS.convert(
                                                  System.currentTimeMillis(),
                                                  TimeUnit.SECONDS
                                              )
                                          ).build()
                                      )
                                      .setBody(body)
                                      .build()

        try {
            blockingStub.sendMessage(message)
        } catch (e: StatusRuntimeException) {
            return false
        }

        return true
    }

    fun shutdown() {
        server.shutdown()
    }

    private class MessengerService(
        private val messageHandler: (Protocol.Message) -> Unit
    ) : MessengerGrpc.MessengerImplBase() {
        override fun sendMessage(
            request: Protocol.Message,
            responseObserver: StreamObserver<Protocol.None>
        ) {
            messageHandler(request)

            responseObserver.onNext(Protocol.None.newBuilder().build())
            responseObserver.onCompleted()
        }
    }
}