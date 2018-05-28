package ru.spbau.bachelor2015.veselov.hw06.model.`interface`

import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.UsableItemViewVisitor

interface UsableItemView : ItemView {
    abstract fun <R> accept(visitor: UsableItemViewVisitor<R>): R
}
