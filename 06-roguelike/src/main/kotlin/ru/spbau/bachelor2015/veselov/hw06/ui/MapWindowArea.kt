package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.screen.Screen
import ru.spbau.bachelor2015.veselov.hw06.model.GameModel
import ru.spbau.bachelor2015.veselov.hw06.model.Vector2D

class MapWindowArea(
    private val gameModel: GameModel,
    screen: Screen,
    areaColumn: Int,
    areaRow: Int,
    width: Int,
    height: Int
) {
    private val borderWindow = Window(
        screen,
        areaColumn,
        areaRow,
        width,
        height
    )

    private val mapWindow = Window(
        screen,
        areaColumn + 1,
        areaRow + 1,
        width - 2,
        height - 2
    )

    fun redraw() {
        val playerCharacter = gameModel.getPlayer()

        val playerColumn = mapWindow.width / 2
        val playerRow = mapWindow.height / 2

        for (column in 0..(mapWindow.width - 1)) {
            for (row in 0..(mapWindow.height - 1)) {
                val vector = Vector2D(column - playerColumn, -(row - playerRow))

                if (!playerCharacter.isPassableRelatively(vector)) {
                    mapWindow.redraw(column, row, TextCharacter(' '))
                    continue
                }

                val objectsInCell = playerCharacter.getObjectsRelatively(vector)

                if (objectsInCell.isEmpty()) {
                    mapWindow.redraw(column, row, TextCharacter('.'))
                    continue
                }

                mapWindow.redraw(column, row, TextCharacter('?'))
            }
        }

        for (column in 0..(borderWindow.width - 1)) {
            borderWindow.redraw(column, 0, TextCharacter('\u2550'))
            borderWindow.redraw(column, borderWindow.height - 1, TextCharacter('\u2550'))
        }

        for (row in 0..(borderWindow.height - 1)) {
            borderWindow.redraw(0, row, TextCharacter('\u2551'))
            borderWindow.redraw(borderWindow.width - 1, row, TextCharacter('\u2551'))
        }

        borderWindow.redraw(0, 0, TextCharacter('\u2554'))
        borderWindow.redraw(borderWindow.width - 1, 0, TextCharacter('\u2557'))
        borderWindow.redraw(0, borderWindow.height - 1, TextCharacter('\u255A'))
        borderWindow.redraw(borderWindow.width - 1, borderWindow.height - 1, TextCharacter('\u255D'))
    }
}
