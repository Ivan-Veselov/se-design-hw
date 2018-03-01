package ru.spbau.bachelor2015.veselov.hw01.compilers

import ru.spbau.bachelor2015.veselov.hw01.commands.Command
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandString

/**
 * CommandCompilers are able to build a Command from a CommandString.
 */
interface CommandCompiler {
    /**
     * @param commandString a command string to compile a command from.
     * @return compiled command.
     */
    fun compile(commandString: CommandString): Command
}

/**
 * DelegatingCommandCompiler as CommandCompiler is able to build a Command, but it needs another
 * CommandCompiler mediator for it.
 */
interface DelegatingCommandCompiler {
    /**
     * @param commandString a command string to compile a command from.
     * @param commandCompiler a mediator compiler which will be used by this delegating compiler.
     * @return compiled command.
     */
    fun compile(commandString: CommandString, commandCompiler: CommandCompiler): Command
}

/**
 * Create a CommandCompiler from DelegatingCommandCompiler and CommandCompiler by passing the
 * second one to the first one as mediator.
 */
fun DelegatingCommandCompiler.delegateTo(commandCompiler: CommandCompiler): CommandCompiler {
    return object : CommandCompiler {
        override fun compile(commandString: CommandString): Command {
            return this@delegateTo.compile(commandString, commandCompiler)
        }
    }
}

/**
 * Creates another DelegatingCommandCompiler by chaining two DelegatingCommandCompilers.
 */
fun DelegatingCommandCompiler.delegateTo(
    delegatingCommandCompiler: DelegatingCommandCompiler
): DelegatingCommandCompiler {
    return object : DelegatingCommandCompiler {
        override fun compile(
            commandString: CommandString,
            commandCompiler: CommandCompiler
        ): Command {
            return this@delegateTo.compile(
                commandString,
                delegatingCommandCompiler.delegateTo(commandCompiler)
            )
        }
    }
}

/**
 * SpecificCommandCompiler might build a Command from a CommandString. But it is possible that
 * it is impossible to build such Command from a given string, in this case SpecificCommandCompiler
 * will return null.
 */
interface SpecificCommandCompiler {
    /**
     * @param commandString a command string to compile a command from.
     * @return compiled command or null if it wasn't possible to build a command from a
     *         given string.
     */
    fun compile(commandString: CommandString): Command?
}

/**
 * Creates another SpecificCommandCompiler which tries to build a Command with this compiler and in
 * case of failure tries to use the second one.
 */
fun SpecificCommandCompiler.thisOrNext(
    commandCompiler: SpecificCommandCompiler
): SpecificCommandCompiler {
    return object : SpecificCommandCompiler {
        override fun compile(commandString: CommandString): Command? {
            return this@thisOrNext.compile(commandString) ?: commandCompiler.compile(commandString)
        }
    }
}

/**
 * Creates CommandCompiler which tries to build a Command with this compiler and in case of failure
 * it uses a simple CommandCompiler to build a Command.
 */
fun SpecificCommandCompiler.thisOrNext(
    commandCompiler: CommandCompiler
): CommandCompiler {
    return object : CommandCompiler {
        override fun compile(commandString: CommandString): Command {
            return this@thisOrNext.compile(commandString) ?: commandCompiler.compile(commandString)
        }
    }
}
