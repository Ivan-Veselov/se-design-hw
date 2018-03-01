package ru.spbau.bachelor2015.veselov.hw01.preprocessing

/**
 * A standard command string preprocessor which makes every character inside double or single
 * quotes escaped. Quotes inside other quotes will also be escaped. Double quotes does not
 * escape '$' character.
 */
object StandardCommandStringPreprocessor : CommandStringPreprocessor {
    /**
     * @inheritDoc
     */
    override fun process(string: String): CommandString {
        var escaping = ESCAPING.NON

        val result = string.map {
            val isEscaped =
            when (escaping) {
                ESCAPING.NON -> {
                    when (it) {
                        CommandCharacter.strongQuote.character -> escaping = ESCAPING.STRONG

                        CommandCharacter.weakQuote.character -> escaping = ESCAPING.WEAK

                        else -> {}
                    }

                    false
                }

                ESCAPING.STRONG -> {
                    when (it) {
                        CommandCharacter.strongQuote.character -> {
                            escaping = ESCAPING.NON
                            false
                        }

                        else -> true
                    }
                }

                ESCAPING.WEAK -> {
                    when (it) {
                        CommandCharacter.weakQuote.character -> {
                            escaping = ESCAPING.NON
                            false
                        }

                        CommandCharacter.symbolExpansion.character -> false

                        else -> true
                    }
                }
            }

            CommandCharacter(it, isEscaped)
        }

        if (escaping != ESCAPING.NON) {
            throw UnmatchedQuoteException()
        }

        return result
    }

    private enum class ESCAPING {
        NON, WEAK, STRONG
    }
}
