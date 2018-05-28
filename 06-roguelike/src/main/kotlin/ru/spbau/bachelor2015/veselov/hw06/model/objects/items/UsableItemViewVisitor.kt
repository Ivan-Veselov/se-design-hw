package ru.spbau.bachelor2015.veselov.hw06.model.objects.items

interface UsableItemViewVisitor<R> {
    fun visit(healingPotion: HealingPotion): R
}
