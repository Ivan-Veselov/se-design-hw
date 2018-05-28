package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.gui2.*
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import ru.spbau.bachelor2015.veselov.hw06.language.Phrases.INVENTORY
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.PlayerCharacterView
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.ItemNameResolver

class InventoryScreen(
    private val screen: Screen,
    private val player: PlayerCharacterView
) {
    fun open() {
        val textGUI = MultiWindowTextGUI(screen)
        // todo: textGUI.setTheme()

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

        player.getInventory().getItems().forEach {
            itemsList.addItem(it.accept(ItemNameResolver)) {
            }
        }

        contentPanel.addComponent(itemsList)
        window.setComponent(contentPanel)

        textGUI.addWindowAndWait(window)
        screen.clear()
    }
}
