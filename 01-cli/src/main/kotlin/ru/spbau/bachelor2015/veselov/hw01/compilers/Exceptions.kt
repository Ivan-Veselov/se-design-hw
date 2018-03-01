package ru.spbau.bachelor2015.veselov.hw01.compilers

/**
 * An exception which might be thrown by a compiler.
 */
open class CompilerException(message: String) : Exception(message)

/**
 * Compiler throws this exception in case when an empty command is encountered.
 */
class EmptyCommandException : CompilerException("An empty command is encountered")

/**
 * Compiler throws this exception if it definitely encountered an assignment command but syntax
 * of this command is incorrect.
 */
class InvalidAssignmentCommandException : CompilerException(
                                                "Invalid assignment command is encountered")