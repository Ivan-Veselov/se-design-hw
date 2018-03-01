package ru.spbau.bachelor2015.veselov.hw01

import ru.spbau.bachelor2015.veselov.hw01.commands.CommandExecutor
import ru.spbau.bachelor2015.veselov.hw01.commands.execute
import ru.spbau.bachelor2015.veselov.hw01.environment.Environment

/**
 * Execution system has inner environment and can execute strings of code.
 */
class ExecutionSystem(
    private val assembler: CommandAssembler,
    private val executor: CommandExecutor,
    private val environment: Environment
) {
    /**
     * Executes given string of code.
     *
     * @param code a string to execute.
     * @return execution result.
     */
   fun execute(code: String): ExecutionResult {
       return executor.execute(assembler.assemble(code), "", environment)
   }
}
