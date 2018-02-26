package ru.spbau.bachelor2015.veselov.hw01

import ru.spbau.bachelor2015.veselov.hw01.commands.StandardCommandExecutor
import ru.spbau.bachelor2015.veselov.hw01.environment.Environment
import ru.spbau.bachelor2015.veselov.hw01.utilities.CatUtility
import ru.spbau.bachelor2015.veselov.hw01.utilities.EchoUtility

fun main(args: Array<String>) {
    val environment = Environment()
    environment.registerUtility("cat", CatUtility)
    environment.registerUtility("echo", EchoUtility)

    val cli =
        CommandLineInterface(
            ExecutionSystem(StandardCommandAssembler, StandardCommandExecutor, environment)
        )

    cli.run()
}
