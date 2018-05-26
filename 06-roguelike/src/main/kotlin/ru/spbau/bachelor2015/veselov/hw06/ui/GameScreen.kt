package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import ru.spbau.bachelor2015.veselov.hw06.model.Direction
import ru.spbau.bachelor2015.veselov.hw06.model.GameModel

class GameScreen(private val gameModel: GameModel, screen: Screen): ConsoleScreen {
    private val mapWindowArea = MapWindowArea(
        gameModel,
        screen,
        0,
        0,
        mapAreaWidth,
        mapAreaHeight
    )

    override fun redraw() {
        mapWindowArea.redraw()
    }

    override fun handleInput(stroke: KeyStroke): ConsoleScreen.Response {
        when (stroke.character) {
            '1' -> gameModel.makePlayerAction(Direction.SOUTHWEST)

            '2' -> gameModel.makePlayerAction(Direction.SOUTH)

            '3' -> gameModel.makePlayerAction(Direction.SOUTHEAST)

            '4' -> gameModel.makePlayerAction(Direction.WEST)

            '6' -> gameModel.makePlayerAction(Direction.EAST)

            '7' -> gameModel.makePlayerAction(Direction.NORTHWEST)

            '8' -> gameModel.makePlayerAction(Direction.NORTH)

            '9' -> gameModel.makePlayerAction(Direction.NORTHEAST)

            else -> {}
        }

        if (gameModel.isWon()) {
            return ConsoleScreen.Response(null, true)
        }

        return ConsoleScreen.Response(null, false)
    }

    private companion object {
        const val mapAreaWidth = 81

        const val mapAreaHeight = 21
    }
}
