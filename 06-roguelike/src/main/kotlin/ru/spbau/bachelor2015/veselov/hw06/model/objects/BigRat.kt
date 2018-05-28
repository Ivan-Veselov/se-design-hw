package ru.spbau.bachelor2015.veselov.hw06.model.objects

import ru.spbau.bachelor2015.veselov.hw06.model.GameObjectPriority
import ru.spbau.bachelor2015.veselov.hw06.model.SpaceManager
import ru.spbau.bachelor2015.veselov.hw06.model.SpatialObjectVisitor
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.SpatialObjectViewVisitor

class BigRat(
    spaceManager: SpaceManager
) : Monster(
    spaceManager,
    GameObjectPriority.MONSTER,
    mapOf(
        Pair(Attribute.HEALTH_AMOUNT, 3),
        Pair(Attribute.DEFENCE, 1),
        Pair(Attribute.ATTACK, 2),
        Pair(Attribute.AGILITY, 2)
    )
) {
    override fun getActualAttribute(attribute: Attribute): Int {
        return getBaseAttribute(attribute)
    }

    override fun willStepOnTheSameCellWith(other: SpaceManager.SpatialObject): Boolean {
        return other.accept(object : SpatialObjectVisitor<Boolean> {
            override fun visit(player: PlayerCharacter): Boolean {
                return false
            }

            override fun visit(exit: Exit): Boolean {
                return true
            }

            override fun visit(monsterAreaCentre: MonsterAreaCentre): Boolean {
                return true
            }

            override fun visit(bigRat: BigRat): Boolean {
                return false
            }
        })
    }

    override fun makeStep() {
        // todo
    }

    override fun <R> accept(visitor: SpatialObjectVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun <R> accept(visitor: SpatialObjectViewVisitor<R>): R {
        return visitor.visit(this)
    }
}