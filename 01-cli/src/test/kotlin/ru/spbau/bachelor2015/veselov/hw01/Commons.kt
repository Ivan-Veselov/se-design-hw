package ru.spbau.bachelor2015.veselov.hw01

import ru.spbau.bachelor2015.veselov.hw01.environment.Utility

object ConcatenationUtility : Utility {
    val triesToExit: Boolean = false

    override fun execute(args: List<String>, input: String): ExecutionResult {
        return ExecutionResult(args.joinToString("") + input, triesToExit)
    }
}
