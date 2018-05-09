package ru.spbau.bachelor2015.veselov.hw06.model.objects

import ru.spbau.bachelor2015.veselov.hw06.model.GameObjectPriority
import ru.spbau.bachelor2015.veselov.hw06.model.SpaceManager

class Exit(
    spaceManager: SpaceManager
) : SpaceManager.SpatialObject(
    spaceManager,
    GameObjectPriority.STATIC_OBJECT
) {
    override fun willStepOnTheSameCellWith(other: SpaceManager.SpatialObject): Boolean {
        return true
    }

    override fun makeStep() { }
}
