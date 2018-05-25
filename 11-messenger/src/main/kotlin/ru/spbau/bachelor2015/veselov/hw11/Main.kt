package ru.spbau.bachelor2015.veselov.hw11

import java.text.SimpleDateFormat
import java.util.*

fun main(args: Array<String>) {
    if (args.size != 4) {
        println("Usage: user-name port peer-address peer-port")
        return
    }

    val userName = args[0]
    val myPort = args[1].toInt()
    val peerAddress = args[2]
    val peerPort = args[3].toInt()

    val messenger = Messenger(userName, myPort, peerAddress, peerPort) {
        val timestamp = SimpleDateFormat().format(Date(it.timestamp.seconds))
        println("[$timestamp] ${it.userName}: ${it.body}")
    }

    while (true) {
        val body = readLine() ?: break
        if (!messenger.sendMessage(body)) {
            println("Failed to deliver previous message")
        }
    }
}
