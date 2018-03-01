package ru.spbau.bachelor2015.veselov.hw01.compilers

import ru.spbau.bachelor2015.veselov.hw01.commands.Command
import ru.spbau.bachelor2015.veselov.hw01.commands.PipelineCommand
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandCharacter
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandString
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.split

/**
 * PipelineCommandCompiler splits passed string with '|' symbol as delimiter and uses another
 * compiler to build all parts separately. All resulting commands are combined together to a
 * chain of PipelineCommands.
 */
object PipelineCommandCompiler : DelegatingCommandCompiler {
    /**
     * @inheritDoc
     */
    override fun compile(commandString: CommandString, commandCompiler: CommandCompiler): Command {
        val commands = commandString.split { it == CommandCharacter.pipe }
                                    .map { commandCompiler.compile(it) }.toMutableList()

        if (commands.isEmpty()) {
            throw EmptyCommandException()
        }

        while (commands.size > 1) {
            val rightCommand = commands.removeAt(commands.lastIndex)
            val leftCommand = commands.removeAt(commands.lastIndex)

            commands.add(PipelineCommand(leftCommand, rightCommand))
        }

        return commands.first()
    }
}
