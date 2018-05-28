package ru.spbau.bachelor2015.veselov.hw06.model.objects.items

interface UsableItemVisitor<R> {
    fun visit(healingPotion: HealingPotion): R
}
