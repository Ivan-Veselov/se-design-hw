package ru.spbau.bachelor2015.veselov.hw11

fun main(args: Array<String>) {
    val john =
        Protocol.Person.newBuilder()
              .setId(1234)
              .setName("John Doe")
              .setEmail("jdoe@example.com")
              .build()
}
