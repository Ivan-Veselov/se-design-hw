package ru.spbau.bachelor2015.veselov.hw06.model

import ru.spbau.bachelor2015.veselov.hw06.model.objects.BattleUnit
import ru.spbau.bachelor2015.veselov.hw06.model.objects.BigRat
import ru.spbau.bachelor2015.veselov.hw06.model.objects.PlayerCharacter
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.ItemHolder
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.ItemNameResolver

/**
 * Log that stores game events messages.
 */
class GameLog {
    private val messages = mutableListOf<String>()

    private val battleUnitNameGiver = object : BattleUnitVisitor<String> {
        override fun visit(player: PlayerCharacter): String {
            return "you"
        }

        override fun visit(bigRat: BigRat): String {
            return "big rat"
        }
    }

    fun addAttackEntry(attacker: BattleUnit, attacked: BattleUnit, damage: Int) {
        messages.add("${attacker.accept(battleUnitNameGiver)} dealt $damage damage to ${attacked.accept(battleUnitNameGiver)}")
    }

    fun addDieEntry(unit: BattleUnit) {
        messages.add("${unit.accept(battleUnitNameGiver)} died")
    }

    fun addPickUpEntry(item: ItemHolder.Item) {
        messages.add("You picked up a ${item.accept(ItemNameResolver)}")
    }

    /**
     * Returns list of all messages.
     */
    fun getMessages(): List<String> {
        return messages
    }
}
