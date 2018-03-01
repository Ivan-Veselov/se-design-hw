package ru.spbau.bachelor2015.veselov.hw01

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import ru.spbau.bachelor2015.veselov.hw01.commands.StandardCommandExecutor
import ru.spbau.bachelor2015.veselov.hw01.environment.Environment

class ExecutionSystemTest {
    private val utilityName = "name"

    private val environment = Environment()

    init {
        environment.registerUtility(utilityName, ConcatenationUtility)
    }

    private val system =
        ExecutionSystem(
            StandardCommandAssembler,
            StandardCommandExecutor,
            environment
        )

    @Test
    fun complexExpression() {
        val executionResult = system.execute("a = $utilityName | \$a b c | $utilityName a")

        assertThat(executionResult, `is`(equalTo(ExecutionResult("abc", false))))
    }
}
