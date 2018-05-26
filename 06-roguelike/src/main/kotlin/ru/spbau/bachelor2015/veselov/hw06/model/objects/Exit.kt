package ru.spbau.bachelor2015.veselov.hw06.model.objects

import ru.spbau.bachelor2015.veselov.hw06.model.GameObjectPriority
import ru.spbau.bachelor2015.veselov.hw06.model.SpaceManager
import ru.spbau.bachelor2015.veselov.hw06.model.SpatialObjectVisitor
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.ExitView
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.SpatialObjectViewVisitor

class Exit(
    spaceManager: SpaceManager
) : SpaceManager.SpatialObject(
    spaceManager,
    GameObjectPriority.STATIC_OBJECT
), ExitView {
    override fun willStepOnTheSameCellWith(other: SpaceManager.SpatialObject): Boolean {
        return true
    }

    override fun <R> accept(visitor: SpatialObjectVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun <R> accept(visitor: SpatialObjectViewVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun makeStep() { }
}
