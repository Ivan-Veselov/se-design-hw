package ru.spbau.bachelor2015.veselov.hw01.tokenization

import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandString

/**
 * Tokenizer can split a given CommandString into a sequence of Tokens.
 */
interface Tokenizer {
    /**
     * @param commandString a command string to split
     * @return a list of tokens obtained from a given CommandString
     */
    fun tokenize(commandString: CommandString): List<Token>
}
