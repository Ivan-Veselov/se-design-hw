package ru.spbau.bachelor2015.veselov.hw01.tokenization

/**
 * Token is an object which can be resolved to a String if SymbolsResolver is given.
 */
interface Token {
    /**
     * @param resolver a SymbolsResolver to resolve a String.
     * @return a resolved String.
     */
    fun resolve(resolver: SymbolsResolver): String
}

/**
 * A token which consist of two tokens which resolved strings should be concatenated to a
 * single string.
 */
data class ConcatenationToken(val firstToken: Token, val secondToken: Token): Token {
    override fun resolve(resolver: SymbolsResolver): String {
        return buildString {
            append(firstToken.resolve(resolver))
            append(secondToken.resolve(resolver))
        }
    }
}

/**
 * A token which resolves string by resolving symbol that it stores.
 */
data class NameToken(val name: String): Token {
    override fun resolve(resolver: SymbolsResolver): String {
        return resolver.resolve(name)
    }
}

/**
 * A token which stores a simple string which is already a result of resolution.
 */
data class StringToken(val string: String): Token {
    override fun resolve(resolver: SymbolsResolver): String {
        return string
    }
}
