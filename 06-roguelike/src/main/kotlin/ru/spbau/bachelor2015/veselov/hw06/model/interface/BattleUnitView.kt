package ru.spbau.bachelor2015.veselov.hw06.model.`interface`

import ru.spbau.bachelor2015.veselov.hw06.model.objects.Attribute

interface BattleUnitView : SpatialObjectView {
    fun getActualAttribute(attribute: Attribute): Int

    fun getCurrentHealth(): Int
}
