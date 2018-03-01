package ru.spbau.bachelor2015.veselov.hw01.preprocessing

/**
 * A special character which can escaped or nonescaped.
 */
data class CommandCharacter(val character: Char, val isEscaped: Boolean) {
    companion object {
        val weakQuote = CommandCharacter('"', false)

        val strongQuote = CommandCharacter('\'', false)

        val symbolExpansion = CommandCharacter('$', false)

        val assignment = CommandCharacter('=', false)

        val pipe = CommandCharacter('|', false)
    }
}

/**
 * A special 'String' of CommandCharacter.
 */
typealias CommandString = List<CommandCharacter>

/**
 * @return a string representation of this CommandString leaving out all escaping flags.
 */
fun CommandString.stringRepresentation() : String {
    return buildString {
        this@stringRepresentation.forEach {
            append(it.character)
        }
    }
}

/**
 * Splits CommandString into a substrings using given predicate to determine delimiters.
 *
 * @param isDelimiter a predicate which should return true for delimiters.
 * @return a List of CommandStrings original CommandString was split into.
 */
fun CommandString.split(isDelimiter: (CommandCharacter) -> Boolean): List<CommandString> {
    val result = mutableListOf<MutableList<CommandCharacter>>(mutableListOf())
    this.forEach {
        if (isDelimiter(it)) {
            result.add(mutableListOf())
        } else {
            result.last().add(it)
        }
    }

    return result
}
