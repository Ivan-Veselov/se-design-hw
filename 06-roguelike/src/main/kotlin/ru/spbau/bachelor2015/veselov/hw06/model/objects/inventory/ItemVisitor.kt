package ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory

interface ItemVisitor<R> {
    fun visit(helmet: Helmet): R

    fun visit(sword: Sword): R

    fun visit(healingPotion: HealingPotion): R

}
