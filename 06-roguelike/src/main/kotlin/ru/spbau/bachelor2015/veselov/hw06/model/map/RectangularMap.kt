package ru.spbau.bachelor2015.veselov.hw06.model.map

import ru.spbau.bachelor2015.veselov.hw06.model.Vector2D

class NotPassableCellException : Exception()

/**
 * A map that has a form of a rectangle.
 */
class RectangularMap(val width: Int, val height: Int) : StaticMap {
    override fun getNumberOfCells(): Int {
        return width * height
    }

    override fun isPassable(coordinates: Vector2D): Boolean {
        return coordinates.x in 0..(width - 1) && coordinates.y in 0..(height - 1)
    }

    override fun distanceBetween(coordinates1: Vector2D, coordinates2: Vector2D): Int {
        if (!isPassable(coordinates1) || !isPassable(coordinates2)) {
            throw NotPassableCellException()
        }

        return coordinates1.subtract(coordinates2).norm()
    }

    override fun uniformlyDistributedCells(amount: Int): List<Vector2D> {
        val allCells = mutableListOf<Vector2D>()
        for (x in 0..(width - 1)) {
            for (y in 0..(height - 1)) {
                allCells.add(Vector2D(x, y))
            }
        }

        if (amount > allCells.size) {
            throw IllegalArgumentException("Too many cells requested")
        }

        allCells.shuffle()
        return allCells.take(amount)
    }
}
