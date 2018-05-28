package ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory

import ru.spbau.bachelor2015.veselov.hw06.language.Phrases
import ru.spbau.bachelor2015.veselov.hw06.model.objects.BattleUnit

class HealingPotion : UsableItem() {
    override fun <R> accept(visitor: ItemVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun use(battleUnit: BattleUnit) {
        super.use(battleUnit)

        battleUnit.heal(2)
    }
}
