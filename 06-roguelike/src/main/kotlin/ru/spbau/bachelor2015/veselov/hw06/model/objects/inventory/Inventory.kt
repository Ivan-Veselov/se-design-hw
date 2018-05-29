package ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory

import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.EquipmentType
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.EquippableItem
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.ItemHolder

/**
 * Inventory that can store items and equip them.
 */
class Inventory : ItemHolder() {
    private val equipment = EquipmentType.values().map { Pair(it, ItemHolder()) }.toMap()

    /**
     * Unequip item of given type.
     */
    fun unequip(type: EquipmentType) {
        equipment[type]!!.getItems().toList().forEach {
            it.placeInto(this)
        }
    }

    /**
     * Equip given item.
     */
    fun equip(item: EquippableItem) {
        if (equipment.containsKey(item.equipmentType)) {
            unequip(item.equipmentType)
        }

        item.placeInto(equipment[item.equipmentType]!!)
    }

    /**
     * Return item that is equipped in a slot of a given type.
     */
    fun getEquippedItem(type: EquipmentType): EquippableItem? {
        return equipment[type]!!.getItems().firstOrNull() as EquippableItem?
    }
}
