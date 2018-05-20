package ru.spbau.bachelor2015.veselov.hw06.model.`interface`

import ru.spbau.bachelor2015.veselov.hw06.model.Vector2D

interface SpatialObjectView {
    fun isPassableRelatively(vector: Vector2D): Boolean

    fun getObjectsRelatively(vector: Vector2D): List<SpatialObjectView>
}
