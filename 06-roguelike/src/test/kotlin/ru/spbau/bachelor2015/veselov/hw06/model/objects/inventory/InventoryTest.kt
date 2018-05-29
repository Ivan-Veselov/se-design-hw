package ru.spbau.bachelor2015.veselov.hw06.model.objects.inventory

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers.contains
import org.junit.Assert.*
import org.junit.Test
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.EquippableItem
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.ItemHolder
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.Sword

class InventoryTest {
    @Test
    fun equipTest() {
        val inventory = Inventory()
        val equippableItem = Sword()

        equippableItem.placeInto(inventory)
        inventory.equip(equippableItem)

        assertThat(inventory.getItems(), `is`(emptyList<ItemHolder.Item>()))
    }

    @Test
    fun unequipTest() {
        val inventory = Inventory()
        val equippableItem = Sword()

        equippableItem.placeInto(inventory)
        inventory.equip(equippableItem)
        inventory.unequip(equippableItem.equipmentType)

        assertThat(inventory.getItems(), contains(equippableItem as ItemHolder.Item))
        assertThat(inventory.getEquippedItem(equippableItem.equipmentType), `is`(null as EquippableItem?))
    }
}