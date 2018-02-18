package ru.spbau.bachelor2015.veselov.hw01.preprocessing

/**
 * A special character which can escaped or nonescaped.
 */
data class CommandCharacter(val character: Char, val isEscaped: Boolean)

/**
 * A special 'String' of CommandCharacter.
 */
typealias CommandString = List<CommandCharacter>
