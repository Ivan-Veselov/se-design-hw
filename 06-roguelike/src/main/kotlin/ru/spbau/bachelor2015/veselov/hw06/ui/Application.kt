package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import ru.spbau.bachelor2015.veselov.hw06.model.GameModel

/**
 * Class that represents an application and have main game cycle.
 */
class Application {
    private val screen: Screen

    init {
        val defaultTerminalFactory = DefaultTerminalFactory()
        val terminal = defaultTerminalFactory.createTerminal()
        screen = TerminalScreen(terminal)
    }

    fun run() {
        screen.use {
            screen.startScreen()
            screen.setCursorPosition(null)

            val screenStack = mutableListOf<ConsoleScreen>()
            screenStack.add(GameScreen(GameModel(), it))

            while (screenStack.isNotEmpty()) {
                val consoleScreen = screenStack.last()

                it.doResizeIfNecessary()
                consoleScreen.redraw()
                it.refresh()

                val stroke: KeyStroke = it.readInput()

                if (stroke.keyType == KeyType.EOF) {
                    screenStack.clear()
                    continue
                }

                val response = consoleScreen.handleInput(stroke)
                if (response.close) {
                    screenStack.removeAt(screenStack.size - 1)
                    screen.clear()
                }

                if (response.newConsoleScreen != null) {
                    screenStack.add(response.newConsoleScreen)
                }
            }
        }
    }
}