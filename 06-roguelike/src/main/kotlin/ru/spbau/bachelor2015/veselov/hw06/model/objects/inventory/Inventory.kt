package ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory

class Inventory : ItemHolder() {
    private val equipment = mutableMapOf<EquipmentType, EquippableItem?>()

    fun unequip(type: EquipmentType) {
        equipment[type]?.placeInto(this)
        equipment[type] = null
    }

    fun equip(item: EquippableItem) {
        if (equipment.containsKey(item.equipmentType)) {
            unequip(item.equipmentType)
        }

        equipment[item.equipmentType] = item
    }

    fun getEquippedItem(type: EquipmentType): EquippableItem? {
        return equipment[type]
    }
}
