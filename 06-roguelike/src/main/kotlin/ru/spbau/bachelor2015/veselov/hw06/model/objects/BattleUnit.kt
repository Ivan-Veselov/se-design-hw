package ru.spbau.bachelor2015.veselov.hw06.model.objects

import ru.spbau.bachelor2015.veselov.hw06.model.SpaceManager
import ru.spbau.bachelor2015.veselov.hw06.model.GameObjectPriority
import java.util.*

enum class Attribute {
    HEALTH_AMOUNT, DEFENCE, ATTACK, AGILITY;
}

abstract class BattleUnit(
    spaceManager: SpaceManager,
    priority: GameObjectPriority,
    attributes: Map<Attribute, Int>
) : SpaceManager.SpatialObject(spaceManager, priority) {
    private var health: Int = getBaseAttribute(Attribute.HEALTH_AMOUNT)

    private val attributes = attributes.toMutableMap()

    fun getBaseAttribute(attribute: Attribute): Int {
        return attributes[attribute] ?: 0
    }

    fun setBaseAttribute(attribute: Attribute, value: Int) {
        attributes[attribute] = value
    }

    abstract fun getActualAttribute(attribute: Attribute): Int

    fun getCurrentHealth(): Int {
        return health
    }

    fun isDead(): Boolean {
        return health == 0
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

        if (other.health <= 0) {
            other.health = 0
            other.destroy()
        }
    }

    private companion object {
        const val maxAgility = 100

        val random = Random()
    }
}
