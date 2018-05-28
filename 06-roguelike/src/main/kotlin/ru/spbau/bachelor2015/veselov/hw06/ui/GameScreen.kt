package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import ru.spbau.bachelor2015.veselov.hw06.language.Phrases.LOSE_PHRASE
import ru.spbau.bachelor2015.veselov.hw06.language.Phrases.WIN_PHRASE
import ru.spbau.bachelor2015.veselov.hw06.model.Direction
import ru.spbau.bachelor2015.veselov.hw06.model.GameModel

class GameScreen(private val gameModel: GameModel, private val screen: Screen): ConsoleScreen {
    private val mapWindowArea = MapWindowArea(
        gameModel,
        screen,
        mapAreaColumn,
        mapAreaRow,
        mapAreaWidth,
        mapAreaHeight
    )

    private val attributesWindowArea = AttributesWindowArea(
        gameModel.getPlayer(),
        screen,
        mapAreaColumn,
        mapAreaRow + mapAreaHeight
    )

    private val logWindowArea = LogWindowArea(
        gameModel.getLog(),
        screen,
        mapAreaColumn + mapAreaWidth,
        mapAreaRow,
        0,
        mapAreaHeight
    )

    override fun redraw() {
        mapWindowArea.redraw()
        attributesWindowArea.redraw()
        logWindowArea.redraw()
    }

    override fun handleInput(stroke: KeyStroke): ConsoleScreen.Response {
        if (stroke.keyType == KeyType.Escape) {
            return ConsoleScreen.Response(null, true)
        }

        when (stroke.character) {
            '1' -> gameModel.makePlayerAction(Direction.SOUTHWEST)

            '2' -> gameModel.makePlayerAction(Direction.SOUTH)

            '3' -> gameModel.makePlayerAction(Direction.SOUTHEAST)

            '4' -> gameModel.makePlayerAction(Direction.WEST)

            '6' -> gameModel.makePlayerAction(Direction.EAST)

            '7' -> gameModel.makePlayerAction(Direction.NORTHWEST)

            '8' -> gameModel.makePlayerAction(Direction.NORTH)

            '9' -> gameModel.makePlayerAction(Direction.NORTHEAST)

            'i' -> InventoryScreen(screen, gameModel).open()

            else -> {}
        }

        if (gameModel.isWon()) {
            return ConsoleScreen.Response(TextScreen(screen, WIN_PHRASE), true)
        }

        if (gameModel.isLost()) {
            return ConsoleScreen.Response(TextScreen(screen, LOSE_PHRASE), true)
        }

        return ConsoleScreen.Response(null, false)
    }

    private companion object {
        const val mapAreaRow = 0

        const val mapAreaColumn = 0

        const val mapAreaWidth = 81

        const val mapAreaHeight = 21
    }
}
