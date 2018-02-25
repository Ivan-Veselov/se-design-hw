package ru.spbau.bachelor2015.veselov.hw01.commands

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import ru.spbau.bachelor2015.veselov.hw01.ConcatenationUtility
import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.environment.Environment
import ru.spbau.bachelor2015.veselov.hw01.tokenization.StringToken

class StandardCommandExecutorTest {
    @Test
    fun utilityCommand() {
        val environment = Environment()

        val utilityName = "name"

        val args = listOf(StringToken("a"), StringToken("b"))
        val input = "c"

        val concatenation = args.joinToString("") { it.resolve(environment) } + input

        environment.registerUtility(utilityName, ConcatenationUtility)

        val executionResult = StandardCommandExecutor.execute(
            UtilityCommand(StringToken(utilityName), args),
            input,
            environment
        )

        assertThat(
            executionResult,
            `is`(equalTo(ExecutionResult(concatenation, ConcatenationUtility.triesToExit)))
        )
    }

    @Test
    fun assignmentCommand() {
        val environment = Environment()

        val name = "name"
        val value = "value"

        val executionResult = StandardCommandExecutor.execute(
            AssignmentCommand(StringToken(name), StringToken(value)),
            "",
            environment
        )

        assertThat(executionResult, `is`(equalTo(ExecutionResult("", false))))
        assertThat(environment.resolve(name), `is`(equalTo(value)))
    }

    @Test
    fun pipelineCommand() {
        val environment = Environment()

        val utilityName = "name"

        val firstArgs = listOf(StringToken("c"), StringToken("d"))
        val input = "e"

        val secondArgs = listOf(StringToken("a"), StringToken("b"))

        val concatenation =
                secondArgs.joinToString("") { it.resolve(environment) } +
                firstArgs.joinToString("") { it.resolve(environment) } +
                input

        environment.registerUtility(utilityName, ConcatenationUtility)

        val executionResult = StandardCommandExecutor.execute(
            PipelineCommand(
                UtilityCommand(StringToken(utilityName), firstArgs),
                UtilityCommand(StringToken(utilityName), secondArgs)),
            input,
            environment
        )

        assertThat(
            executionResult,
            `is`(equalTo(ExecutionResult(concatenation, ConcatenationUtility.triesToExit)))
        )
    }
}
