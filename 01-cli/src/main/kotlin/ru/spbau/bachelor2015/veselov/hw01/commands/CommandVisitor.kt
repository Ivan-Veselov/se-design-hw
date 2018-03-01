package ru.spbau.bachelor2015.veselov.hw01.commands

/**
 * A visitor pattern for Command interface.
 */
interface CommandVisitor<out R> {
    fun visit(command: PipelineCommand): R

    fun visit(command: AssignmentCommand): R

    fun visit(command: UtilityCommand): R
}
