package ru.spbau.bachelor2015.veselov.hw01.commands

import ru.spbau.bachelor2015.veselov.hw01.tokenization.Token

/**
 * A Command is a structural representation of a piece of code.
 */
interface Command {
    /**
     * Accept method for visitor pattern.
     */
    fun <R> accept(visitor: CommandVisitor<R>): R
}

/**
 * PipelineCommand is a command which redirects output stream of one command to another one.
 */
data class PipelineCommand(val firstCommand: Command, val secondCommand: Command) : Command {
    override fun <R> accept(visitor: CommandVisitor<R>): R {
        return visitor.visit(this)
    }
}

/**
 * AssignmentCommand is a command which sets value to some environment symbol.
 */
data class AssignmentCommand(val name: Token, val value: Token) : Command {
    override fun <R> accept(visitor: CommandVisitor<R>): R {
        return visitor.visit(this)
    }
}

/**
 * UtilityCommand is a command which just runs utility.
 */
data class UtilityCommand(val name: Token, val args: List<Token>) : Command {
    override fun <R> accept(visitor: CommandVisitor<R>): R {
        return visitor.visit(this)
    }
}
