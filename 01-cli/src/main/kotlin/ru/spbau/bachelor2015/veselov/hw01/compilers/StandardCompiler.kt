package ru.spbau.bachelor2015.veselov.hw01.compilers

import ru.spbau.bachelor2015.veselov.hw01.commands.Command
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandString
import ru.spbau.bachelor2015.veselov.hw01.tokenization.Tokenizer

/**
 * A standard compiler which builds a pipeline chain of commands. Each command in pipeline is either
 * an assignment command or an utility command if it is not possible to build an assignment command
 * from corresponding part.
 */
class StandardCompiler(tokenizer: Tokenizer) : CommandCompiler {
    private val underlyingCompiler =
        PipelineCommandCompiler.delegateTo(
            AssignmentCommandCompiler(tokenizer).thisOrNext(
                UtilityCommandCompiler(tokenizer)
            )
        )

    /**
     * @inheritDoc
     */
    override fun compile(commandString: CommandString): Command {
        return underlyingCompiler.compile(commandString)
    }
}
