package ru.spbau.bachelor2015.veselov.hw01.tokenization

/**
 * Objects with this interface can resolve symbol to its value-string.
 */
interface SymbolsResolver {
    /**
     * @param symbol a symbol which should be resolved.
     * @return a value-string which associated with given symbol.
     */
    fun resolve(symbol: String): String
}
