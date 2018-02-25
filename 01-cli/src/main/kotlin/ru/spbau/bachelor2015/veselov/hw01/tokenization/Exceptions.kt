package ru.spbau.bachelor2015.veselov.hw01.tokenization

/**
 * An exception which might be thrown by a tokenizer.
 */
open class TokenizerException(message: String) : Exception(message)

/**
 * Tokenizer throws this exception when quotation symbols in input cannot be matched properly.
 */
class UnmatchedQuotationSymbolException : TokenizerException("Unmatched quotation symbol present")
