package ru.spbau.bachelor2015.veselov.hw01

import ru.spbau.bachelor2015.veselov.hw01.commands.Command
import ru.spbau.bachelor2015.veselov.hw01.compilers.CommandCompiler
import ru.spbau.bachelor2015.veselov.hw01.compilers.StandardCompiler
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandStringPreprocessor
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.StandardCommandStringPreprocessor
import ru.spbau.bachelor2015.veselov.hw01.tokenization.StandardTokenizer

/**
 * CommandAssembler consists of preprocessor and compiler. It can assemble a command from a given
 * string by applying to it preprocessor and compiler consecutively.
 */
class CommandAssembler(
    private val preprocessor: CommandStringPreprocessor,
    private val compiler: CommandCompiler
) {
    /**
     * @param code a string representation of a command.
     * @return assembled command.
     */
    fun assemble(code: String): Command {
        return compiler.compile(preprocessor.process(code))
    }
}

val StandardCommandAssembler =
    CommandAssembler(
        StandardCommandStringPreprocessor,
        StandardCompiler(StandardTokenizer)
    )
