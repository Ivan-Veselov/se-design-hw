package ru.spbau.bachelor2015.veselov.hw06.model.objects

import ru.spbau.bachelor2015.veselov.hw06.model.*
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.SpatialObjectViewVisitor
import java.util.*

class BigRat(
    spaceManager: SpaceManager,
    private val homeCentre: MonsterAreaCentre,
    private val radius: Int,
    private val player: PlayerCharacter
) : Monster(
    spaceManager,
    GameObjectPriority.MONSTER,
    mapOf(
        Pair(Attribute.HEALTH_AMOUNT, 3),
        Pair(Attribute.DEFENCE, 0),
        Pair(Attribute.ATTACK, 2),
        Pair(Attribute.AGILITY, 2)
    )
) {
    private val random = Random()

    override fun getActualAttribute(attribute: Attribute): Int {
        return getBaseAttribute(attribute)
    }

    override fun willStepOnTheSameCellWith(other: SpaceManager.SpatialObject): Boolean {
        return other.accept(object : SpatialObjectVisitor<Boolean> {
            override fun visit(chest: Chest): Boolean {
                return true
            }

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
        if (this.distanceTo(homeCentre) > 2 * radius) {
            returnStep()
            return
        }

        if (!player.isDead() && homeCentre.distanceTo(player) <= radius) {
            attackStep()
            return
        }

        patrolStep()
    }

    private fun patrolStep() {
        val vectors = Direction.values()
                               .filter { this.canBeTranslatedOn(it.vector) }
                               .map { it.vector }

        if (vectors.isEmpty()) {
            return
        }

        val index = random.nextInt(vectors.size)
        this.translateOn(vectors[index])
    }

    private fun returnStep() {
        moveTo(homeCentre)
    }

    private fun attackStep() {
        if (this.isInAdjacentCell(player)) {
            this.attack(player)
            return
        }

        moveTo(player)
    }

    private fun moveTo(spatialObject: SpaceManager.SpatialObject) {
        val vector = this.distanceDistributionTo(spatialObject).minBy { it.value }?.key?.vector

        if (vector != null) {
            this.translateOn(vector)
        }
    }

    override fun <R> accept(visitor: SpatialObjectVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun <R> accept(visitor: SpatialObjectViewVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun <R> accept(visitor: BattleUnitVisitor<R>): R {
        return visitor.visit(this)
    }
}