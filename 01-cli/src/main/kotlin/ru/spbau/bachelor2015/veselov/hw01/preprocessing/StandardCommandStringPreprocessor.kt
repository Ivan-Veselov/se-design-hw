package ru.spbau.bachelor2015.veselov.hw01.preprocessing

/**
 * A standard command string preprocessor which makes everything inside double or single quotes
 * as escaped characters. Quotes inside other quotes will also be escaped. Double quotes does not
 * escape '$' character.
 */
object StandardCommandStringPreprocessor : CommandStringPreprocessor {
    override fun process(string: String): CommandString {
        var escaping = ESCAPING.NON

        return string.map {
            val isEscaped =
            when (escaping) {
                ESCAPING.NON -> {
                    when (it) {
                        '\'' -> escaping = ESCAPING.STRONG

                        '"' -> escaping = ESCAPING.WEAK

                        else -> {}
                    }

                    false
                }

                ESCAPING.STRONG -> {
                    when (it) {
                        '\'' -> {
                            escaping = ESCAPING.NON
                            false
                        }

                        else -> true
                    }
                }

                ESCAPING.WEAK -> {
                    when (it) {
                        '"' -> {
                            escaping = ESCAPING.NON
                            false
                        }

                        '$' -> false

                        else -> true
                    }
                }
            }

            CommandCharacter(it, isEscaped)
        }
    }

    private enum class ESCAPING {
        NON, WEAK, STRONG
    }
}
