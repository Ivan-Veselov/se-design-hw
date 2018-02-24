package ru.spbau.bachelor2015.veselov.hw01.preprocessing

/**
 * An exception which might be thrown by a preprocessor.
 */
open class PreprocessorException(message: String) : Exception(message)

/**
 * Preprocessor throws this exception when quotes in input cannot be matched properly.
 */
class UnmatchedQuoteException : PreprocessorException("Unmatched quote present")
