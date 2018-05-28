package ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory

import ru.spbau.bachelor2015.veselov.hw06.model.objects.Attribute

enum class EquipmentType {
    ARMOR, WEAPON
}

abstract class EquippableItem : ItemHolder.Item() {
    abstract val equipmentType: EquipmentType

    abstract fun getAttributeInfluence(attribute: Attribute): Int
}
