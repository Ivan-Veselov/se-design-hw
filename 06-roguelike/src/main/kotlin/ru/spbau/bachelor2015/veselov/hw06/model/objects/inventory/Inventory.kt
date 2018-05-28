package ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory

import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.EquipmentType
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.EquippableItem
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.ItemHolder

class Inventory : ItemHolder() {
    private val equipment = EquipmentType.values().map { Pair(it, ItemHolder()) }.toMap()

    fun unequip(type: EquipmentType) {
        equipment[type]!!.getItems().toList().forEach {
            it.placeInto(this)
        }
    }

    fun equip(item: EquippableItem) {
        if (equipment.containsKey(item.equipmentType)) {
            unequip(item.equipmentType)
        }

        item.placeInto(equipment[item.equipmentType]!!)
    }

    fun getEquippedItem(type: EquipmentType): EquippableItem? {
        return equipment[type]!!.getItems().firstOrNull() as EquippableItem?
    }
}
