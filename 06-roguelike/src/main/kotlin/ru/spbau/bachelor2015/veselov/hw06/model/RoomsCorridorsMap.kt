package ru.spbau.bachelor2015.veselov.hw06.model

class RoomsCorridorsMap(
    private val roomsInColumn: Int,
    private val roomsInRow: Int
) : StaticMap {
    override fun isPassable(coordinates: Vector2D): Boolean {
        TODO("not implemented")
    }

    override fun distanceBetween(coordinates1: Vector2D, coordinates2: Vector2D): Int {
        TODO("not implemented")
    }

    override fun uniformlyDistributedCells(amount: Int): List<Vector2D> {
        TODO("not implemented")
    }
}