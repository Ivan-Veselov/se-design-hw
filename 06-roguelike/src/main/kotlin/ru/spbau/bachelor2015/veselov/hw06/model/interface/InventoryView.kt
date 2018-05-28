package ru.spbau.bachelor2015.veselov.hw06.model.`interface`

import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.EquipmentType
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.EquippableItem

interface InventoryView {
    fun unequip(type: EquipmentType)

    fun equip(item: EquippableItem)

    fun getEquippedItem(type: EquipmentType): EquippableItem?

    fun getItems(): List<ItemView>
}
