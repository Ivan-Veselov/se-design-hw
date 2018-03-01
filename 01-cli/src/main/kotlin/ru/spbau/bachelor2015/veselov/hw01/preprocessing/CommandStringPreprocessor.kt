package ru.spbau.bachelor2015.veselov.hw01.preprocessing

/**
 * A preprocessor which builds a CommandString from an ordinary String.
 */
interface CommandStringPreprocessor {
    /**
     * @param string an ordinary Java String.
     * @return a CommandString representation of a given string.
     */
    fun process(string: String): CommandString
}
