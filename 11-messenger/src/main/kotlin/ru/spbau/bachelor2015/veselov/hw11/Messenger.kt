package ru.spbau.bachelor2015.veselov.hw11

import com.google.protobuf.Timestamp
import io.grpc.ManagedChannelBuilder
import io.grpc.ServerBuilder
import io.grpc.StatusRuntimeException
import io.grpc.stub.StreamObserver
import java.util.concurrent.TimeUnit
import java.util.logging.FileHandler
import java.util.logging.Level
import java.util.logging.Logger
import java.util.logging.SimpleFormatter



/**
 * Messenger represents a peer-to-peer connection with another user.
 */
class Messenger(
    private val userName: String,
    port: Int,
    peerAddress: String,
    peerPort: Int,
    messageHandler: (Protocol.Message) -> Unit
) {
    private val logger = Logger.getLogger(Messenger::class.java.name)

    init {
        logger.useParentHandlers = false
        logger.level = Level.FINEST

        val handler = FileHandler("log")
        handler.formatter = SimpleFormatter()
        logger.addHandler(handler)
    }

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

        logger.info("Creating channel")
        val channel = ManagedChannelBuilder.forAddress(peerAddress, peerPort)
                                           .usePlaintext()
                                           .build()

        blockingStub = MessengerGrpc.newBlockingStub(channel)

        logger.info("Messenger is initialized")
    }

    /**
     * Sends message.
     *
     * @param body text body of a message.
     */
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
            logger.warning(e.toString())
            logger.exiting(Messenger::class.java.name, "sendMessage", false)
            return false
        }

        logger.exiting(Messenger::class.java.name, "sendMessage", true)
        return true
    }

    /**
     * Closes this connection.
     */
    fun shutdown() {
        server.shutdown()
    }

    private class MessengerService(
        private val messageHandler: (Protocol.Message) -> Unit
    ) : MessengerGrpc.MessengerImplBase() {
        private val logger = Logger.getLogger(Messenger::class.java.name)

        override fun sendMessage(
            request: Protocol.Message,
            responseObserver: StreamObserver<Protocol.None>
        ) {
            logger.entering(MessengerService::class.java.name, "sendMessage")

            messageHandler(request)

            responseObserver.onNext(Protocol.None.newBuilder().build())
            responseObserver.onCompleted()

            logger.exiting(MessengerService::class.java.name, "sendMessage")
        }
    }
}
