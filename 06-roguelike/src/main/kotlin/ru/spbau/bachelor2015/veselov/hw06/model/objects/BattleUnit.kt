package ru.spbau.bachelor2015.veselov.hw06.model.objects

import ru.spbau.bachelor2015.veselov.hw06.model.BattleUnitVisitor
import ru.spbau.bachelor2015.veselov.hw06.model.GameObjectPriority
import ru.spbau.bachelor2015.veselov.hw06.model.SpaceManager
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.BattleUnitView
import java.lang.Integer.min
import java.util.*

enum class Attribute {
    HEALTH_AMOUNT, DEFENCE, ATTACK, AGILITY;
}

/**
 * A space object that can fight.
 */
abstract class BattleUnit(
    spaceManager: SpaceManager,
    priority: GameObjectPriority,
    attributes: Map<Attribute, Int>
) : SpaceManager.SpatialObject(spaceManager, priority), BattleUnitView {
    private val attributes = attributes.toMutableMap()

    private var health: Int = getBaseAttribute(Attribute.HEALTH_AMOUNT)

    abstract fun <R> accept(visitor: BattleUnitVisitor<R>): R

    /**
     * Returns base value of an given attribute.
     */
    fun getBaseAttribute(attribute: Attribute): Int {
        return attributes[attribute] ?: 0
    }

    /**
     * Changes base value of a given attribute.
     */
    fun setBaseAttribute(attribute: Attribute, value: Int) {
        attributes[attribute] = value
    }

    override fun getCurrentHealth(): Int {
        return health
    }

    /**
     * Returns true if health of this unit is equal to zero.
     */
    fun isDead(): Boolean {
        return health == 0
    }

    /**
     * Adds given amount of health points to this unit.
     */
    fun heal(points: Int) {
        health = min(health + points, getBaseAttribute(Attribute.HEALTH_AMOUNT))
    }

    /**
     * Attack another unit and decreases it's health.
     */
    fun attack(other: BattleUnit) {
        val maxDamage =
            getActualAttribute(Attribute.ATTACK) - other.getBaseAttribute(Attribute.DEFENCE)

        if (maxDamage <= 0) {
            return
        }

        if (random.nextInt(maxAgility) + 1 <= other.getActualAttribute(Attribute.AGILITY)) {
            return
        }

        val damage = random.nextInt(maxDamage + 1)
        other.health -= damage

        getLog().addAttackEntry(this, other, damage)

        if (other.health <= 0) {
            other.health = 0
            getLog().addDieEntry(other)
            other.destroy()
        }
    }

    private companion object {
        const val maxAgility = 100

        val random = Random()
    }
}
