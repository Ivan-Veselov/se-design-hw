package ru.spbau.bachelor2015.veselov.hw11

fun main(args: Array<String>) {
    if (args.size != 3) {
        println("Usage: port peer-address peer-port")
        return
    }

    val myPort = args[0].toInt()
    val peerAddress = args[1]
    val peerPort = args[2].toInt()

    val messenger = Messenger(myPort, peerAddress, peerPort) {
        println(it.body)
    }

    while (true) {
        val body = readLine() ?: break
        if (!messenger.sendMessage(body)) {
            println("Failed to deliver previous message")
        }
    }
}
