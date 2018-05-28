package ru.spbau.bachelor2015.veselov.hw06.model.objects.items

import ru.spbau.bachelor2015.veselov.hw06.model.objects.BattleUnit

abstract class UsableItem : ItemHolder.Item() {
    abstract fun <R> accept(visitor: UsableItemVisitor<R>): R
}
