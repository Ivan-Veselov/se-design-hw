package ru.spbau.bachelor2015.veselov.hw06.model

import ru.spbau.bachelor2015.veselov.hw06.model.objects.BattleUnit
import ru.spbau.bachelor2015.veselov.hw06.model.objects.BigRat
import ru.spbau.bachelor2015.veselov.hw06.model.objects.PlayerCharacter

class GameLog {
    private val messages = mutableListOf<String>()

    private val nameGiver = object : BattleUnitVisitor<String> {
        override fun visit(player: PlayerCharacter): String {
            return "you"
        }

        override fun visit(bigRat: BigRat): String {
            return "big rat"
        }
    }

    fun addAttackEntry(attacker: BattleUnit, attacked: BattleUnit, damage: Int) {
        messages.add("${attacker.accept(nameGiver)} deals $damage damage to ${attacked.accept(nameGiver)}")
    }

    fun addDieEntry(unit: BattleUnit) {
        messages.add("${unit.accept(nameGiver)} dies")
    }

    fun getMessages(): List<String> {
        return messages
    }
}
