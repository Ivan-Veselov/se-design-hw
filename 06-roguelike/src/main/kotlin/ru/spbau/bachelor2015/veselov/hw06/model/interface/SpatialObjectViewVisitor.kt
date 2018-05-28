package ru.spbau.bachelor2015.veselov.hw06.model.`interface`

import ru.spbau.bachelor2015.veselov.hw06.model.objects.MonsterAreaCentre

interface SpatialObjectViewVisitor<R> {
    fun visit(player: PlayerCharacterView): R

    fun visit(exit: ExitView): R

    fun visit(monsterAreaCentre: MonsterAreaCentreView): R
}