package ru.spbau.bachelor2015.veselov.hw06.model.map

import ru.spbau.bachelor2015.veselov.hw06.model.Vector2D

interface StaticMap {
    fun isPassable(coordinates: Vector2D): Boolean

    fun distanceBetween(coordinates1: Vector2D, coordinates2: Vector2D): Int

    fun uniformlyDistributedCells(amount: Int): List<Vector2D>

    fun getNumberOfCells(): Int
}
