package ru.spbau.bachelor2015.veselov.hw01.commands

import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.environment.Environment

/**
 * CommandExecutors might execute command in some environment with a given input stream.
 */
interface CommandExecutor {
    fun execute(
        command: PipelineCommand,
        input: String,
        environment: Environment
    ): ExecutionResult

    fun execute(
        command: AssignmentCommand,
        input: String,
        environment: Environment
    ): ExecutionResult

    fun execute(
        command: UtilityCommand,
        input: String,
        environment: Environment
    ): ExecutionResult
}

fun CommandExecutor.execute(
    command: Command,
    input: String,
    environment: Environment
): ExecutionResult {
    return command.accept(object : CommandVisitor<ExecutionResult> {
        override fun visit(command: PipelineCommand): ExecutionResult {
            return this@execute.execute(command, input, environment)
        }

        override fun visit(command: AssignmentCommand): ExecutionResult {
            return this@execute.execute(command, input, environment)
        }

        override fun visit(command: UtilityCommand): ExecutionResult {
            return this@execute.execute(command, input, environment)
        }
    })
}
