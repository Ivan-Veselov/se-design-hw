package ru.spbau.bachelor2015.veselov.hw06.model.objects.items

import ru.spbau.bachelor2015.veselov.hw06.model.objects.Attribute

class Sword : EquippableItem() {
    override val equipmentType: EquipmentType = EquipmentType.WEAPON

    override fun <R> accept(visitor: ItemVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun getAttributeInfluence(attribute: Attribute): Int {
        if (attribute == Attribute.ATTACK) {
            return 2
        }

        return 0
    }
}
