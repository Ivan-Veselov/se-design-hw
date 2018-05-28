package ru.spbau.bachelor2015.veselov.hw06.model.objects

import ru.spbau.bachelor2015.veselov.hw06.model.BattleUnitVisitor
import ru.spbau.bachelor2015.veselov.hw06.model.GameObjectPriority
import ru.spbau.bachelor2015.veselov.hw06.model.SpaceManager
import ru.spbau.bachelor2015.veselov.hw06.model.SpatialObjectVisitor
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.PlayerCharacterView
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.SpatialObjectViewVisitor
import ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory.Inventory
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.EquipmentType

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
    override val inventory = Inventory()

    override fun willStepOnTheSameCellWith(other: SpaceManager.SpatialObject): Boolean {
        return other.accept(object : SpatialObjectVisitor<Boolean> {
            override fun visit(chest: Chest): Boolean {
                return true
            }

            override fun visit(bigRat: BigRat): Boolean {
                return false
            }

            override fun visit(monsterAreaCentre: MonsterAreaCentre): Boolean {
                return true
            }

            override fun visit(player: PlayerCharacter): Boolean {
                return true
            }

            override fun visit(exit: Exit): Boolean {
                return true
            }
        })
    }

    override fun <R> accept(visitor: SpatialObjectViewVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun <R> accept(visitor: SpatialObjectVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun <R> accept(visitor: BattleUnitVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun getActualAttribute(attribute: Attribute): Int {
        return EquipmentType.values().map {
            inventory.getEquippedItem(it)?.getAttributeInfluence(attribute)
        }.sumBy { it ?: 0 } + getBaseAttribute(attribute)
    }

    override fun makeStep() { }
}
