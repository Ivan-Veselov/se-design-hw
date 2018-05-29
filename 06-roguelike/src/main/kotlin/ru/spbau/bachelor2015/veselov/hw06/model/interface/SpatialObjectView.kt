package ru.spbau.bachelor2015.veselov.hw06.model.`interface`

import ru.spbau.bachelor2015.veselov.hw06.model.Vector2D

/**
 * Public interface of SpatialObject
 */
interface SpatialObjectView {
    /**
     * Returns true if cell that can be obtained by adding argument to current position of this
     * unit is passable.
     */
    fun isPassableRelatively(vector: Vector2D): Boolean

    /**
     * Return all objects in cell that can be obtained by adding argument to current position
     * of this unit.
     */
    fun getObjectsRelatively(vector: Vector2D): List<SpatialObjectView>

    fun <R> accept(visitor: SpatialObjectViewVisitor<R>): R
}
