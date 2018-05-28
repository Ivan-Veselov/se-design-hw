package ru.spbau.bachelor2015.veselov.hw06.model.objects.items

import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.UsableItemView
import ru.spbau.bachelor2015.veselov.hw06.model.objects.BattleUnit

object ItemApplier {
    fun useItemOn(unit: BattleUnit, item: UsableItemView) {
        item.accept(object : UsableItemViewVisitor<Unit> {
            override fun visit(healingPotion: HealingPotion) {
                unit.heal(2)
                healingPotion.remove()
            }
        })
    }
}
