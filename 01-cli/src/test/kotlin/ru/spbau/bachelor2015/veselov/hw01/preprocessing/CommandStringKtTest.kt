package ru.spbau.bachelor2015.veselov.hw01.preprocessing

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers.contains
import org.junit.Assert.*
import org.junit.Test

class CommandStringKtTest {
    private val spaces: (CommandCharacter) -> Boolean = {
        it.character == ' '
    }

    @Test
    fun stringRepresentation() {
        assertThat(
            listOf(
                CommandCharacter('a', true),
                CommandCharacter('b', true),
                CommandCharacter('a', false)
            ).stringRepresentation(),
            `is`(equalTo("aba"))
        )
    }

    @Test
    fun simpleSplit() {
        val firstPart = listOf(CommandCharacter('a', true))
        val delimiter = listOf(CommandCharacter(' ', true))
        val secondPart = listOf(CommandCharacter('b', false))

        assertThat(
            (firstPart + delimiter + secondPart).split(spaces),
            contains(firstPart, secondPart)
        )
    }

    @Test
    fun splitWithTrickyDelimiters() {
        val prefix = listOf(CommandCharacter(' ', true))
        val firstPart = listOf(CommandCharacter('a', true))
        val delimiter = listOf(
                            CommandCharacter(' ', true),
                            CommandCharacter(' ', true)
                        )
        val secondPart = listOf(CommandCharacter('b', false))
        val suffix = listOf(CommandCharacter(' ', true))

        assertThat(
                (prefix + firstPart + delimiter + secondPart + suffix).split(spaces),
                contains(listOf(), firstPart, listOf(), secondPart, listOf())
        )
    }
}
