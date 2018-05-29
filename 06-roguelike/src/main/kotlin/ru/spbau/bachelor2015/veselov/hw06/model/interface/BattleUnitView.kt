package ru.spbau.bachelor2015.veselov.hw06.model.`interface`

import ru.spbau.bachelor2015.veselov.hw06.model.objects.Attribute

/**
 * Public interface of BattleUnit
 */
interface BattleUnitView : SpatialObjectView {
    /**
     * Returns actual value (with all effects applied) of given attribute.
     *
     * @param attribute which value is requested
     * @return integer value of attribute
     */
    fun getActualAttribute(attribute: Attribute): Int

    /**
     * Returns current health of this unit.
     */
    fun getCurrentHealth(): Int
}
