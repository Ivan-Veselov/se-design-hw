package ru.spbau.bachelor2015.veselov.hw06.model.`interface`

interface SpatialObjectViewVisitor<R> {
    fun visit(player: PlayerCharacterView): R

    fun visit(player: ExitView): R
}