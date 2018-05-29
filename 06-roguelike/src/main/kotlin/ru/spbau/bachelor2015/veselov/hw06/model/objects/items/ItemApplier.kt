package ru.spbau.bachelor2015.veselov.hw06.model.objects.items

import ru.spbau.bachelor2015.veselov.hw06.model.objects.BattleUnit

object ItemApplier {
    /**
     * Applies given usable item to given unit.
     */
    fun useItemOn(unit: BattleUnit, item: UsableItem) {
        item.accept(object : UsableItemVisitor<Unit> {
            override fun visit(healingPotion: HealingPotion) {
                unit.heal(2)
                healingPotion.remove()
            }
        })
    }
}
