package ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory

import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.InventoryView
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.EquipmentType
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.EquippableItem
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.ItemHolder

class Inventory : ItemHolder(), InventoryView {
    private val equipment = mutableMapOf<EquipmentType, EquippableItem?>()

    override fun unequip(type: EquipmentType) {
        equipment[type]?.placeInto(this)
        equipment[type] = null
    }

    override fun equip(item: EquippableItem) {
        if (equipment.containsKey(item.equipmentType)) {
            unequip(item.equipmentType)
        }

        equipment[item.equipmentType] = item
    }

    override fun getEquippedItem(type: EquipmentType): EquippableItem? {
        return equipment[type]
    }
}
