package ru.spbau.bachelor2015.veselov.hw06.model.objects.items

import ru.spbau.bachelor2015.veselov.hw06.model.objects.Attribute

class Helmet : EquippableItem() {
    override val equipmentType: EquipmentType = EquipmentType.ARMOR

    override fun <R> accept(visitor: ItemVisitor<R>): R {
        return visitor.visit(this)
    }

    override fun getAttributeInfluence(attribute: Attribute): Int {
        if (attribute == Attribute.DEFENCE) {
            return 2
        }

        if (attribute == Attribute.AGILITY) {
            return -1
        }

        return 0
    }
}
