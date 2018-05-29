package ru.spbau.bachelor2015.veselov.hw06.model.objects.items

/**
 * Item that can be used.
 */
abstract class UsableItem : ItemHolder.Item() {
    abstract fun <R> accept(visitor: UsableItemVisitor<R>): R
}
