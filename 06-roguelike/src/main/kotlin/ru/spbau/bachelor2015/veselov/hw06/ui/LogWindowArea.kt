package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.screen.Screen
import ru.spbau.bachelor2015.veselov.hw06.model.GameLog

class LogWindowArea(
    private val log: GameLog,
    screen: Screen,
    areaColumn: Int,
    areaRow: Int,
    width: Int,
    private val height: Int
) {
    private val topLeft = TerminalPosition(areaColumn, areaRow)

    private val textGraphics = screen.newTextGraphics()

    fun redraw() {
        val messages = log.getMessages().takeLast(height)
        for (index in 0 until messages.size) {
            textGraphics.putString(topLeft.withRelativeRow(index), messages[index])
        }
    }
}