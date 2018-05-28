package ru.spbau.bachelor2015.veselov.hw06.model.objects.items

class HealingPotion : UsableItem() {
    override fun <R> accept(visitor: ItemVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun <R> accept(visitor: UsableItemVisitor<R>): R {
        return visitor.visit(this)
    }
}
