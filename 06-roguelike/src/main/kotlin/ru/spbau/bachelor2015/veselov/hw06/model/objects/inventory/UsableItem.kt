package ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory

import ru.spbau.bachelor2015.veselov.hw06.model.objects.BattleUnit

abstract class UsableItem : ItemHolder.Item() {
    open fun use(battleUnit: BattleUnit) {
        this.remove()
    }
}
