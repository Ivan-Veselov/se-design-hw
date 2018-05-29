package ru.spbau.bachelor2015.veselov.hw06.model.objects.items

import ru.spbau.bachelor2015.veselov.hw06.model.objects.Attribute

enum class EquipmentType {
    ARMOR, WEAPON
}

/**
 * Items that can be equipped.
 */
abstract class EquippableItem : ItemHolder.Item() {
    abstract val equipmentType: EquipmentType

    /**
     * Returns integer change of a given attribute in case when this item is equipped.
     */
    abstract fun getAttributeInfluence(attribute: Attribute): Int
}
