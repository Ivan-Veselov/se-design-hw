package ru.spbau.bachelor2015.veselov.hw01.compilers

import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Test
import ru.spbau.bachelor2015.veselov.hw01.commands.AssignmentCommand
import ru.spbau.bachelor2015.veselov.hw01.commands.Command
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandCharacter
import ru.spbau.bachelor2015.veselov.hw01.tokenization.StandardTokenizer
import ru.spbau.bachelor2015.veselov.hw01.tokenization.StringToken

class AssignmentCommandCompilerTest {
    private val compiler = AssignmentCommandCompiler(StandardTokenizer)

    @Test
    fun simpleAssignment() {
        val commandString = listOf(
            CommandCharacter('a', false),
            CommandCharacter(' ', false),
            CommandCharacter.assignment,
            CommandCharacter(' ', false),
            CommandCharacter('b', false)
        )

        val command = AssignmentCommand(StringToken("a"), StringToken("b")) as Command?

        assertThat(
            compiler.compile(commandString),
            CoreMatchers.`is`(CoreMatchers.equalTo(command))
        )
    }

    @Test
    fun noAssignment() {
        val commandString = listOf(
            CommandCharacter('a', false),
            CommandCharacter(' ', false),
            CommandCharacter(' ', false),
            CommandCharacter('b', false)
        )

        val command = compiler.compile(commandString)

        assertThat(command, `is`(equalTo(null as Command?)))
    }
}
