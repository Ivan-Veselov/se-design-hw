package ru.spbau.bachelor2015.veselov.hw01

import ru.spbau.bachelor2015.veselov.hw01.commands.StandardCommandExecutor
import ru.spbau.bachelor2015.veselov.hw01.environment.Environment

fun main(args: Array<String>) {
    val environment = Environment()
    val cli =
        CommandLineInterface(
            ExecutionSystem(StandardCommandAssembler, StandardCommandExecutor, environment)
        )

    cli.run()
}
