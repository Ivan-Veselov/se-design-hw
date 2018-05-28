package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.Screen

class TextScreen(private val screen: Screen, private val text: String) : ConsoleScreen {
    private val topLeft = TerminalPosition(1, 1)

    private val textGraphics = screen.newTextGraphics()

    override fun redraw() {
        textGraphics.putString(topLeft, text)
    }

    override fun handleInput(stroke: KeyStroke): ConsoleScreen.Response {
        return ConsoleScreen.Response(null, true)
    }
}