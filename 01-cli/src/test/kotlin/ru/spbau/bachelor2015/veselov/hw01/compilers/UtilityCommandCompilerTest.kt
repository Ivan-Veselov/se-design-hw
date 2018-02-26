package ru.spbau.bachelor2015.veselov.hw01.compilers

import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Test
import ru.spbau.bachelor2015.veselov.hw01.commands.Command
import ru.spbau.bachelor2015.veselov.hw01.commands.UtilityCommand
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandCharacter
import ru.spbau.bachelor2015.veselov.hw01.tokenization.StandardTokenizer
import ru.spbau.bachelor2015.veselov.hw01.tokenization.StringToken

class UtilityCommandCompilerTest {
    private val compiler = UtilityCommandCompiler(StandardTokenizer)

    @Test
    fun simpleUtilityCommand() {
        val commandString = listOf(
            CommandCharacter('a', false),
            CommandCharacter(' ', false),
            CommandCharacter(' ', false),
            CommandCharacter('b', false)
        )

        val command = UtilityCommand(
                StringToken("a"),
                listOf(StringToken("b"))
        ) as Command

        assertThat(
            compiler.compile(commandString),
            CoreMatchers.`is`(CoreMatchers.equalTo(command))
        )
    }
}
