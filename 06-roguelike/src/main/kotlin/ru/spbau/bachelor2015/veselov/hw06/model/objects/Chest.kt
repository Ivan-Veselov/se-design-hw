package ru.spbau.bachelor2015.veselov.hw06.model.objects

import ru.spbau.bachelor2015.veselov.hw06.model.GameObjectPriority
import ru.spbau.bachelor2015.veselov.hw06.model.SpaceManager
import ru.spbau.bachelor2015.veselov.hw06.model.SpatialObjectVisitor
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.ChestView
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.SpatialObjectViewVisitor
import ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory.HealingPotion
import ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory.Helmet
import ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory.Sword
import java.util.*

class Chest(
    spaceManager: SpaceManager,
    private val player: PlayerCharacter
): SpaceManager.SpatialObject(
    spaceManager,
    GameObjectPriority.STATIC_OBJECT
), ChestView {
    override fun willStepOnTheSameCellWith(other: SpaceManager.SpatialObject): Boolean {
        return true
    }

    override fun makeStep() {
        if (this.isOnTheSameCell(player)) {
            val item = when (random.nextInt(3)) {
                0 -> HealingPotion()

                1 -> Helmet()

                2 -> Sword()

                else -> throw RuntimeException()
            }

            getLog().addPickUpEntry(item)
            item.placeInto(player.inventory)
            destroy()
        }
    }

    override fun <R> accept(visitor: SpatialObjectVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun <R> accept(visitor: SpatialObjectViewVisitor<R>): R {
        return visitor.visit(this)
    }

    private companion object {
        val random = Random()
    }
}
