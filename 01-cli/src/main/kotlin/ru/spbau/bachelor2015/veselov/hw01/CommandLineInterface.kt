package ru.spbau.bachelor2015.veselov.hw01

/**
 * A REPL command line interface for execution system.
 */
class CommandLineInterface(private val system: ExecutionSystem) {
    /**
     * Runs REPL cycle.
     */
    fun run() {
        while (true) {
            val input = readLine() ?: break

            try {
                val executionResult = system.execute(input)
                println(executionResult.output)

                if (executionResult.triesToExit) {
                    break
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
