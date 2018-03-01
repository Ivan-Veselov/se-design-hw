package ru.spbau.bachelor2015.veselov.hw01.environment

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Test
import ru.spbau.bachelor2015.veselov.hw01.ConcatenationUtility
import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult

class EnvironmentTest {
    @Test
    fun registeredSymbol() {
        val env = Environment()
        env.setSymbol("a","b")

        assertThat(env.resolve("a"), `is`(equalTo("b")))
    }

    @Test
    fun unregisteredSymbol() {
        val env = Environment()

        assertThat(env.resolve("a"), `is`(equalTo("")))
    }

    @Test
    fun registeredUtility() {
        val utilityName = "name"

        val args = listOf("a", "b")
        val input = "c"
        val concatenation = args.joinToString("") + input

        val env = Environment()
        env.registerUtility(utilityName, ConcatenationUtility)

        val executionResult = env.executeUtility(utilityName, args, input)
        assertThat(
            executionResult,
            `is`(equalTo(ExecutionResult(concatenation, ConcatenationUtility.triesToExit)))
        )
    }

    @Test
    fun unregisteredUtility() {
        val message = "abc"

        val env = Environment()
        val executionResult = env.executeUtility("echo", listOf(message), "")

        assertThat(
            executionResult,
            `is`(equalTo(ExecutionResult(message + "\n", false)))
        )
    }
}