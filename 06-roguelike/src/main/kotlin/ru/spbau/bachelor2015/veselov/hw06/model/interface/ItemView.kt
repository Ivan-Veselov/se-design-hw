package ru.spbau.bachelor2015.veselov.hw06.model.`interface`

import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.ItemVisitor

interface ItemView {
    fun <R> accept(visitor: ItemVisitor<R>): R
}
