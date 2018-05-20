package ru.spbau.bachelor2015.veselov.hw06

import com.googlecode.lanterna.screen.TerminalScreen
import ru.spbau.bachelor2015.veselov.hw06.model.GameModel
import ru.spbau.bachelor2015.veselov.hw06.ui.GameScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory

fun main(args: Array<String>) {
    val defaultTerminalFactory = DefaultTerminalFactory()
    val terminal = defaultTerminalFactory.createTerminal()
    val screen = TerminalScreen(terminal)

    screen.use {
        screen.startScreen()
        screen.setCursorPosition(null)
        GameScreen(GameModel(), screen).open()
    }
}
