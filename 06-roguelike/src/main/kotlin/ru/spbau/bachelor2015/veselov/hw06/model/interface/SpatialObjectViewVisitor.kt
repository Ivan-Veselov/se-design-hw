package ru.spbau.bachelor2015.veselov.hw06.model.`interface`

interface SpatialObjectViewVisitor<R> {
    fun visit(player: PlayerCharacterView): R

    fun visit(exit: ExitView): R

    fun visit(monsterAreaCentre: MonsterAreaCentreView): R

    fun visit(monster: MonsterView): R

    fun visit(chest: ChestView): R
}