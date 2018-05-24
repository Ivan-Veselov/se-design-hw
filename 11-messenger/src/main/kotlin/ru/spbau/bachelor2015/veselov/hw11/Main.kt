package ru.spbau.bachelor2015.veselov.hw11

import io.grpc.ManagedChannelBuilder
import io.grpc.ServerBuilder
import io.grpc.StatusRuntimeException

fun main(args: Array<String>) {
    if (args.size != 3) {
        println("Usage: port peer-address peer-port")
        return
    }

    val myPort = args[0].toInt()
    val peerAddress = args[1]
    val peerPort = args[2].toInt()

    val server = ServerBuilder.forPort(myPort)
                              .addService(MessengerService())
                              .build()
                              .start()

    Runtime.getRuntime().addShutdownHook(object : Thread() {
        override fun run() {
            server.shutdown()
        }
    })

    val channel = ManagedChannelBuilder.forAddress(peerAddress, peerPort)
                                       .usePlaintext()
                                       .build()

    val blockingStub = MessengerGrpc.newBlockingStub(channel)

    while (true) {
        val body = readLine()

        val message = Protocol.Message.newBuilder()
                                      .setBody(body)
                                      .build()

        try {
            blockingStub.sendMessage(message)
        } catch (e: StatusRuntimeException) {
            println("Failed to deliver previous message")
        }
    }
}
