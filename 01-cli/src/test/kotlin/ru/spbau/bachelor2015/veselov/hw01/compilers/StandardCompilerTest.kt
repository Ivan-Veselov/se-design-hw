package ru.spbau.bachelor2015.veselov.hw01.compilers

import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Test
import ru.spbau.bachelor2015.veselov.hw01.commands.AssignmentCommand
import ru.spbau.bachelor2015.veselov.hw01.commands.Command
import ru.spbau.bachelor2015.veselov.hw01.commands.PipelineCommand
import ru.spbau.bachelor2015.veselov.hw01.commands.UtilityCommand
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandCharacter
import ru.spbau.bachelor2015.veselov.hw01.tokenization.StandardTokenizer
import ru.spbau.bachelor2015.veselov.hw01.tokenization.StringToken

class StandardCompilerTest {
    private val compiler = StandardCompiler(StandardTokenizer)

    @Test
    fun simpleUtilityCommand() {
        val commandString = listOf(
            CommandCharacter('a', false),
            CommandCharacter.pipe,
            CommandCharacter('a', false),
            CommandCharacter.assignment,
            CommandCharacter('b', false)
        )

        val command =
            PipelineCommand(
                UtilityCommand(StringToken("a"), listOf()),
                AssignmentCommand(StringToken("a"), StringToken("b"))
            ) as Command

        assertThat(
            compiler.compile(commandString),
            CoreMatchers.`is`(CoreMatchers.equalTo(command))
        )
    }
}