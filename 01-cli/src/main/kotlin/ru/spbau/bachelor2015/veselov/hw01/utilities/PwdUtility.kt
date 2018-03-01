package ru.spbau.bachelor2015.veselov.hw01.utilities

import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.environment.Utility

/**
 * Pwd utility returns absolute path of current working directory.
 */
object PwdUtility : Utility {
    override fun execute(args: List<String>, input: String): ExecutionResult {
        return ExecutionResult(System.getProperty("user.dir") + "\n", false)
    }
}
