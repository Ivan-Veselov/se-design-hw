package ru.spbau.bachelor2015.veselov.hw01.compilers

import ru.spbau.bachelor2015.veselov.hw01.commands.Command
import ru.spbau.bachelor2015.veselov.hw01.commands.UtilityCommand
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandString
import ru.spbau.bachelor2015.veselov.hw01.tokenization.Tokenizer

/**
 * A compiler which builds UtilityCommand.
 */
class UtilityCommandCompiler(private val tokenizer: Tokenizer) : CommandCompiler {
    /**
     * @inheritDoc
     */
    override fun compile(commandString: CommandString): Command {
        val tokens = tokenizer.tokenize(commandString)

        if (tokens.isEmpty()) {
            throw EmptyCommandException()
        }

        return UtilityCommand(tokens.first(), tokens.drop(1))
    }
}
