package ru.spbau.bachelor2015.veselov.hw01.utilities

import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.environment.Utility

object EchoUtility : Utility {
    override fun execute(args: List<String>, input: String): ExecutionResult {
        return ExecutionResult(
            args.joinToString(" "),
            false
        )
    }
}