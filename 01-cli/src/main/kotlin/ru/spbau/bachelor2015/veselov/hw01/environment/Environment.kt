package ru.spbau.bachelor2015.veselov.hw01.environment

import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.tokenization.SymbolsResolver
import java.nio.charset.Charset

/**
 * Environment is an object which stores two mappings: one between names and symbols and the other
 * between names and utilities. Environment can execute utility.
 */
class Environment : SymbolsResolver {
    private val symbols: MutableMap<String, String> = mutableMapOf()

    private val utilities: MutableMap<String, Utility> = mutableMapOf()

    /**
     * Registers an utility.
     *
     * @param name name of this utility.
     * @param utility utility which should be registered.
     */
    fun registerUtility(name: String, utility: Utility) {
        utilities[name] = utility
    }

    /**
     * Executes utility with a given name. If there is no utility with such name then system
     * utility will be executed.
     *
     * @param name name of utility which should be executed.
     * @param args arguments which should be passed to utility.
     * @param input content of input stream of utility that is executed.
     * @return execution result.
     */
    fun executeUtility(name: String, args: List<String>, input: String): ExecutionResult {
        return utilities[name]?.execute(args, input) ?:
               executeUnregisteredUtility(name, args, input)
    }

    /**
     * Sets a value to of a symbol.
     *
     * @param name name of a symbol.
     * @param value a value for a symbol.
     */
    fun setSymbol(name: String, value: String) {
        symbols[name] = value
    }

    /**
     * @inheritDoc
     */
    override fun resolve(symbol: String): String {
        return symbols.getOrDefault(symbol, "")
    }

    private fun executeUnregisteredUtility(
        name: String,
        args: List<String>,
        input: String
    ): ExecutionResult {
        val process = Runtime.getRuntime().exec(arrayOf(name) + args.toTypedArray())

        process.outputStream.write(input.toByteArray())
        process.waitFor()

        return ExecutionResult(
            process.inputStream.readBytes().toString(Charset.defaultCharset()),
            process.exitValue() != 0
        )
    }
}
