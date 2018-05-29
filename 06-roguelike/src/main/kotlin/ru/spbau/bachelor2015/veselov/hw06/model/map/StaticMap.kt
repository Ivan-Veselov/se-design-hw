package ru.spbau.bachelor2015.veselov.hw06.model.map

import ru.spbau.bachelor2015.veselov.hw06.model.Vector2D

/**
 * A static map that stores information about passable cells.
 */
interface StaticMap {
    /**
     * Return true if given cell is passable.
     */
    fun isPassable(coordinates: Vector2D): Boolean

    /**
     * Calculates distance between two given passable cells.
     */
    fun distanceBetween(coordinates1: Vector2D, coordinates2: Vector2D): Int

    /**
     * Returns requested amount of cells that has uniform distribution.
     */
    fun uniformlyDistributedCells(amount: Int): List<Vector2D>

    /**
     * Returns number of passable cells.
     */
    fun getNumberOfCells(): Int
}
