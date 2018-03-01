package ru.spbau.bachelor2015.veselov.hw01

/**
 * A result of command execution. Contains a String of output and boolean flag which
 * states whether or not this command wants to exit.
 */
data class ExecutionResult(val output: String, val triesToExit: Boolean)
