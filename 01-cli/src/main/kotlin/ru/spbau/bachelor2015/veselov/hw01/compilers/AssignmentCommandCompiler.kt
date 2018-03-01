package ru.spbau.bachelor2015.veselov.hw01.compilers

import ru.spbau.bachelor2015.veselov.hw01.commands.AssignmentCommand
import ru.spbau.bachelor2015.veselov.hw01.commands.Command
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandCharacter
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandString
import ru.spbau.bachelor2015.veselov.hw01.tokenization.Tokenizer

/**
 * AssignmentCommandCompiler tries to build an AssignmentCommand. If string contains unescaped '='
 * character then it is thought that this string represents an assignment command. Both left and
 * right parts of assignment should have exactly one single token.
 */
class AssignmentCommandCompiler(private val tokenizer: Tokenizer) : SpecificCommandCompiler {
    /**
     * @inheritDoc
     */
    override fun compile(commandString: CommandString): Command? {
        val index = commandString.indexOf(CommandCharacter.assignment)

        if (index == -1) {
            return null
        }

        val leftTokens = tokenizer.tokenize(commandString.take(index))
        val rightTokens = tokenizer.tokenize(commandString.drop(index + 1))

        if (leftTokens.size != 1 || rightTokens.size != 1) {
            throw InvalidAssignmentCommandException()
        }

        return AssignmentCommand(leftTokens.first(), rightTokens.first())
    }
}
