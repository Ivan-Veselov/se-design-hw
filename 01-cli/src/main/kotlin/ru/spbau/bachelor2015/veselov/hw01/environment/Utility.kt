package ru.spbau.bachelor2015.veselov.hw01.environment

import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult

/**
 * An utility which can be executed.
 */
interface Utility {
    /**
     * Executes this utility.
     *
     * @param args passed arguments.
     * @param input input stream.
     * @return execution result.
     */
    fun execute(args: List<String>, input: String): ExecutionResult
}
