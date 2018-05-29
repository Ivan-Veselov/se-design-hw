package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.input.KeyStroke

/**
 * This interface represents an object that is responsible for the whole screen of a console
 * but has a fixed layout.
 */
interface ConsoleScreen {
    fun redraw()

    fun handleInput(stroke: KeyStroke): Response

    data class Response(val newConsoleScreen: ConsoleScreen?, val close: Boolean)
}