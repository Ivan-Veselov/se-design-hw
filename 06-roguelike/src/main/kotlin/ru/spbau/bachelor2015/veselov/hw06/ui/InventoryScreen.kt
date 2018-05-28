package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.SimpleTheme
import com.googlecode.lanterna.gui2.*
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import ru.spbau.bachelor2015.veselov.hw06.language.Phrases.INVENTORY
import ru.spbau.bachelor2015.veselov.hw06.model.GameModel
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.UsableItemView
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.ItemNameResolver

class InventoryScreen(
    private val screen: Screen,
    private val gameModel: GameModel
) {
    fun open() {
        val textGUI = MultiWindowTextGUI(screen)
        textGUI.theme = SimpleTheme.makeTheme(
            true,
                TextColor.ANSI.WHITE,
                TextColor.ANSI.BLACK,
                TextColor.ANSI.BLACK,
                TextColor.ANSI.WHITE,
                TextColor.ANSI.BLACK,
                TextColor.ANSI.WHITE,
                TextColor.ANSI.BLACK
        )

        val window = BasicWindow(INVENTORY)
        window.setHints(listOf(
            Window.Hint.FULL_SCREEN,
            Window.Hint.NO_DECORATIONS,
            Window.Hint.MODAL,
            Window.Hint.NO_POST_RENDERING
        ))

        textGUI.addListener { _, keyStroke ->
            if (keyStroke.keyType == KeyType.Escape) {
                window.close()
            }

            return@addListener false
        }

        val contentPanel = Panel(LinearLayout(Direction.VERTICAL))
        val itemsList = ActionListBox(TerminalSize(14, 10))

        gameModel.getPlayer().getInventory().getItems().forEach {
            itemsList.addItem(it.accept(ItemNameResolver)) {
                if (it is UsableItemView) {
                    gameModel.useItem(it)
                    window.close()
                }
            }
        }

        contentPanel.addComponent(itemsList)
        window.setComponent(contentPanel)

        textGUI.addWindowAndWait(window)
        screen.clear()
    }
}
