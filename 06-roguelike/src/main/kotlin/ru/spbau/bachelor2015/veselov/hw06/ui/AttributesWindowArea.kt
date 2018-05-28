package ru.spbau.bachelor2015.veselov.hw06.ui

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.screen.Screen
import ru.spbau.bachelor2015.veselov.hw06.language.Phrases
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.PlayerCharacterView
import ru.spbau.bachelor2015.veselov.hw06.model.objects.Attribute


class AttributesWindowArea(
    private val player: PlayerCharacterView,
    screen: Screen,
    areaColumn: Int,
    areaRow: Int
) {
    private val topLeft = TerminalPosition(areaColumn, areaRow)

    private val textGraphics = screen.newTextGraphics()

    fun redraw() {
        textGraphics.putString(topLeft, renderToString())
    }

    private fun renderToString(): String {
        return Attribute.values().joinToString(separator = " | ") {
            renderToString(it)
        }
    }

    private fun renderToString(attribute: Attribute): String {
        return buildString {
            when (attribute) {
                Attribute.HEALTH_AMOUNT -> append(Phrases.HEALTH_AMOUNT)

                Attribute.DEFENCE -> append(Phrases.DEFENCE)

                Attribute.ATTACK -> append(Phrases.ATTACK)

                Attribute.AGILITY -> append(Phrases.AGILITY)
            }

            append(": ")

            if (attribute == Attribute.HEALTH_AMOUNT) {
                append(player.getCurrentHealth())
                append(" / ")
            }

            append(player.getActualAttribute(attribute))
        }
    }
}
