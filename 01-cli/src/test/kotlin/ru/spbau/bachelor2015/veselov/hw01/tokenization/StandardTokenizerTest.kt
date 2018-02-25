package ru.spbau.bachelor2015.veselov.hw01.tokenization

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.empty
import org.junit.Assert.assertThat
import org.junit.Test
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandCharacter

class StandardTokenizerTest {
    @Test
    fun emptyString() {
        assertThat(StandardTokenizer.tokenize(listOf()), `is`(empty()))
    }

    @Test
    fun strongQuoting() {
        val commandString = listOf(
            CommandCharacter('\'', false),
            CommandCharacter('"', true),
            CommandCharacter('$', true),
            CommandCharacter('"', true),
            CommandCharacter('\'', false)
        )

        assertThat(
            StandardTokenizer.tokenize(commandString),
            contains(StringToken("\"$\"") as Token)
        )
    }

    @Test
    fun weakQuotingWithSymbolExpansion() {
        val commandString = listOf(
            CommandCharacter('"', false),
            CommandCharacter('\'', true),
            CommandCharacter('$', false),
            CommandCharacter('a', true),
            CommandCharacter('\'', true),
            CommandCharacter('"', false)
        )

        assertThat(
            StandardTokenizer.tokenize(commandString),
            contains(
                ConcatenationToken(
                    StringToken("'"),
                    NameToken("a'")
                ) as Token
            )
        )
    }

    @Test
    fun concatenation() {
        val commandString = listOf(
            CommandCharacter('a', false),
            CommandCharacter('\'', false),
            CommandCharacter('b', true),
            CommandCharacter('\'', false),
            CommandCharacter('c', false)
        )

        assertThat(
            StandardTokenizer.tokenize(commandString),
            contains(
                ConcatenationToken(
                    StringToken("a"),
                    ConcatenationToken(
                        StringToken("b"),
                        StringToken("c")
                    )
                ) as Token
            )
        )
    }
}