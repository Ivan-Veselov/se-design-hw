package ru.spbau.bachelor2015.veselov.hw06.model.objects.items

import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.ItemView

open class ItemHolder {
    private val items = mutableSetOf<Item>()

    fun getItems(): List<Item> {
        return items.toList()
    }

    abstract class Item : ItemView {
        private var place: ItemHolder? = null

        fun placeInto(holder: ItemHolder) {
            if (place != null) {
                place!!.items.remove(this)
            }

            place = holder
            holder.items.add(this)
        }

        fun remove() {
            if (place != null) {
                place!!.items.remove(this)
            }

            place = null
        }
    }
}
