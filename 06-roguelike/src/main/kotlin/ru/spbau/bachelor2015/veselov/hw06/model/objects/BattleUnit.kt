package ru.spbau.bachelor2015.veselov.hw06.model.objects

import ru.spbau.bachelor2015.veselov.hw06.model.BattleUnitVisitor
import ru.spbau.bachelor2015.veselov.hw06.model.SpaceManager
import ru.spbau.bachelor2015.veselov.hw06.model.GameObjectPriority
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.BattleUnitView
import java.lang.Integer.max
import java.util.*

enum class Attribute {
    HEALTH_AMOUNT, DEFENCE, ATTACK, AGILITY;
}

abstract class BattleUnit(
    spaceManager: SpaceManager,
    priority: GameObjectPriority,
    attributes: Map<Attribute, Int>
) : SpaceManager.SpatialObject(spaceManager, priority), BattleUnitView {
    private val attributes = attributes.toMutableMap()

    private var health: Int = getBaseAttribute(Attribute.HEALTH_AMOUNT)

    abstract fun <R> accept(visitor: BattleUnitVisitor<R>): R

    fun getBaseAttribute(attribute: Attribute): Int {
        return attributes[attribute] ?: 0
    }

    fun setBaseAttribute(attribute: Attribute, value: Int) {
        attributes[attribute] = value
    }

    override fun getCurrentHealth(): Int {
        return health
    }

    fun isDead(): Boolean {
        return health == 0
    }

    fun heal(points: Int) {
        health = max(health + points, getBaseAttribute(Attribute.HEALTH_AMOUNT))
    }

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
