package ru.spbau.bachelor2015.veselov.hw06.model.objects.items

open class ItemHolder {
    private val items = mutableSetOf<Item>()

    fun getItems(): List<Item> {
        return items.toList()
    }

    abstract class Item {
        private var place: ItemHolder? = null

        abstract fun <R> accept(visitor: ItemVisitor<R>): R

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
