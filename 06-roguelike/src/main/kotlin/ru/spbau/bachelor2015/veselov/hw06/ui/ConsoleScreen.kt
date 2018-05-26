package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.input.KeyStroke

interface ConsoleScreen {
    fun redraw()

    fun handleInput(stroke: KeyStroke): Response

    data class Response(val newConsoleScreen: ConsoleScreen?, val close: Boolean)
}