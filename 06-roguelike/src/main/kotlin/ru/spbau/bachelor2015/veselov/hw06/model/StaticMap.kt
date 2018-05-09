package ru.spbau.bachelor2015.veselov.hw06.model

interface StaticMap {
    fun isPassable(coordinates: Vector2D): Boolean

    fun distanceBetween(coordinates1: Vector2D, coordinates2: Vector2D): Int

    fun uniformlyDistributedCells(amount: Int): List<Vector2D>
}
