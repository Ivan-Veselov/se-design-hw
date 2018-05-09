package ru.spbau.bachelor2015.veselov.hw06.model.objects

import ru.spbau.bachelor2015.veselov.hw06.model.GameObjectPriority
import ru.spbau.bachelor2015.veselov.hw06.model.SpaceManager
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.PlayerCharacterView

class PlayerCharacter(
    spaceManager: SpaceManager
) : BattleUnit(
    spaceManager,
    GameObjectPriority.LEAST,
    mapOf(
        Pair(Attribute.HEALTH_AMOUNT, 10),
        Pair(Attribute.DEFENCE, 1),
        Pair(Attribute.ATTACK, 2),
        Pair(Attribute.AGILITY, 5)
    )
), PlayerCharacterView {
    override fun willStepOnTheSameCellWith(other: SpaceManager.SpatialObject): Boolean {
        return false // TODO
    }

    override fun getActualAttribute(attribute: Attribute): Int {
        return getBaseAttribute(attribute)
    }

    override fun makeStep() { }
}
