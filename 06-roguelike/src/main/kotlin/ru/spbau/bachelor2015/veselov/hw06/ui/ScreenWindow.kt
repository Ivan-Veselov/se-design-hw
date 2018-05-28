package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.screen.Screen

class ScreenWindow(
    private val screen: Screen,
    private val windowColumn: Int,
    private val windowRow: Int,
    val width: Int,
    val height: Int
) {
    fun draw(column: Int, row: Int, character: TextCharacter) {
        screen.setCharacter(
            windowColumn + column,
            windowRow + row,
            character
        )
    }
}
