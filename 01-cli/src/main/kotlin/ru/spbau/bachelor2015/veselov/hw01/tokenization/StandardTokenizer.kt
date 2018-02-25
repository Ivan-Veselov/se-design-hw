package ru.spbau.bachelor2015.veselov.hw01.tokenization

import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandCharacter
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandString
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.split
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.stringRepresentation

/**
 * A standard tokenizer which uses whitespaces to separate tokens from each other. '$' sign creates
 * standalone NameTokens. Quotes escape special characters so that everything inside quotes
 * becomes one single Token. This is not true for double quotes and '$' sign. Double quotes do not
 * escape '$' sign.
 */
object StandardTokenizer : Tokenizer {
    /**
     * @inheritDoc
     */
    override fun tokenize(commandString: CommandString): List<Token> {
        return commandString.split {
            !it.isEscaped && Character.isWhitespace(it.character)
        }.filter {
                    it.isNotEmpty()
                }.map {
                    concatenationTokenFrom(it)
                }
    }

    private fun concatenationTokenFrom(commandString: CommandString): Token {
        assert(commandString.isNotEmpty())

        fun processToken(
                stopCharacter: (CommandCharacter) -> Boolean,
                processToken: (CommandString) -> Token,
                skipPrefix: Boolean,
                skipStopCharacter: Boolean,
                missingStopIsError: Boolean
        ): Token {
            val indexOfNext = commandString.drop(1)
                    .indexOfFirst(stopCharacter) + 1


            if (indexOfNext == 0) {
                if (missingStopIsError) {
                    throw UnmatchedQuotationSymbolException()
                }

                return processToken(
                        commandString.subList(if (skipPrefix) 1 else 0, commandString.size)
                )
            }

            val numberOfCharactersToDrop = indexOfNext + if (skipStopCharacter) 1 else 0
            if (numberOfCharactersToDrop == commandString.size) {
                return processToken(commandString.subList(if (skipPrefix) 1 else 0, indexOfNext))
            }

            return ConcatenationToken(
                    processToken(commandString.subList(if (skipPrefix) 1 else 0, indexOfNext)),
                    concatenationTokenFrom(
                            commandString.drop(numberOfCharactersToDrop)
                    )
            )
        }

        return when (commandString.first()) {
            CommandCharacter.weakQuote -> {
                processToken(
                        { it == CommandCharacter.weakQuote },
                        { tokenFromWeaklyQuotedString(it) },
                        true,
                        true,
                        true
                )
            }

            CommandCharacter.strongQuote -> {
                processToken(
                        { it == CommandCharacter.strongQuote },
                        { tokenFromStronglyQuotedString(it) },
                        true,
                        true,
                        true
                )
            }

            CommandCharacter.symbolExpansion -> {
                processToken(
                        {
                            it == CommandCharacter.strongQuote ||
                                    it == CommandCharacter.weakQuote ||
                                    it == CommandCharacter.symbolExpansion ||
                                    Character.isWhitespace(it.character)
                        },
                        { tokenFromSymbolExpansion(it) },
                        true,
                        false,
                        false
                )
            }

            else -> {
                processToken(
                        {
                            it == CommandCharacter.strongQuote ||
                                    it == CommandCharacter.weakQuote ||
                                    it == CommandCharacter.symbolExpansion
                        },
                        { tokenFromSimpleString(it) },
                        false,
                        false,
                        false
                )
            }
        }
    }

    private fun tokenFromStronglyQuotedString(commandString: CommandString): StringToken {
        return StringToken(commandString.stringRepresentation())
    }

    private fun tokenFromWeaklyQuotedString(commandString: CommandString): Token {
        return concatenationTokenFrom(commandString)
    }

    private fun tokenFromSymbolExpansion(commandString: CommandString): NameToken {
        return NameToken(commandString.stringRepresentation())
    }

    private fun tokenFromSimpleString(commandString: CommandString): StringToken {
        return StringToken(commandString.stringRepresentation())
    }
}
