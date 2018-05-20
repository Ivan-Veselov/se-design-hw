package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import ru.spbau.bachelor2015.veselov.hw06.model.Direction
import ru.spbau.bachelor2015.veselov.hw06.model.GameModel

class GameScreen(private val gameModel: GameModel, private val screen: Screen) {
    private val mapWindowArea = MapWindowArea(
        gameModel,
        screen,
        0,
        0,
        mapAreaWidth,
        mapAreaHeight
    )

    fun open() {
        var shouldRun = true

        while (shouldRun) {
            screen.doResizeIfNecessary()
            mapWindowArea.update()
            screen.refresh()

            val stroke: KeyStroke = screen.readInput()

            when (stroke.keyType) {
                KeyType.ArrowLeft -> gameModel.makePlayerAction(Direction.WEST)

                KeyType.ArrowRight -> gameModel.makePlayerAction(Direction.EAST)

                KeyType.ArrowUp -> gameModel.makePlayerAction(Direction.NORTH)

                KeyType.ArrowDown -> gameModel.makePlayerAction(Direction.SOUTH)

                KeyType.EOF -> shouldRun = false

                else -> {}
            }
        }
    }

    private companion object {
        const val mapAreaWidth = 81

        const val mapAreaHeight = 21
    }
}
