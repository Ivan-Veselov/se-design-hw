package ru.spbau.bachelor2015.veselov.hw06.model.`interface`

import ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory.Inventory

/**
 * Public interface of PlayerCharacter
 */
interface PlayerCharacterView : BattleUnitView {
    /**
     * Players inventory
     */
    val inventory: Inventory
}
