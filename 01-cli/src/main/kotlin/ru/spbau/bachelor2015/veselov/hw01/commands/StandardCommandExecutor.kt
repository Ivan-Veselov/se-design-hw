package ru.spbau.bachelor2015.veselov.hw01.commands

import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.environment.Environment

/**
 * Standard CommandExecutor which might execute Pipeline, Assignment and Utility commands.
 */
object StandardCommandExecutor : CommandExecutor {
    override fun execute(
        command: PipelineCommand,
        input: String,
        environment: Environment
    ): ExecutionResult {
        val firstResult = execute(command.firstCommand, input, environment)
        if (firstResult.triesToExit) {
            return firstResult
        }

        return execute(command.secondCommand, firstResult.output, environment)
    }

    override fun execute(
        command: AssignmentCommand,
        input: String,
        environment: Environment
    ): ExecutionResult {
        environment.setSymbol(
            command.name.resolve(environment),
            command.value.resolve(environment)
        )

        return ExecutionResult("", false)
    }

    override fun execute(
        command: UtilityCommand,
        input: String,
        environment: Environment
    ): ExecutionResult {
        return environment.executeUtility(
            command.name.resolve(environment),
            command.args.map { it.resolve(environment) },
            input
        )
    }
}
