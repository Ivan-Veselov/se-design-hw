package ru.spbau.bachelor2015.veselov.hw06.model

import java.util.*

class InvalidCellToPutOnException: Exception()

class ObjectWithNoPositionException: Exception()

class SpaceManager(
    private val gameObjectsManager: GameObjectsManager,
    private val staticMap: StaticMap
) {
    private val objectCoordinates = mutableMapOf<SpatialObject, Vector2D>()

    private val objectsInCell = mutableMapOf<Vector2D, MutableSet<SpatialObject>>()

    abstract inner class SpatialObject(
        priority: GameObjectPriority
    ): GameObjectsManager.GameObject(gameObjectsManager, priority) {
        abstract fun willStepOnTheSameCellWith(other: SpatialObject): Boolean

        fun isInAdjacentCell(other: SpatialObject): Boolean {
            val myCoordinates = objectCoordinates[this]
            val otherCoordinates = objectCoordinates[other]

            if (myCoordinates == null || otherCoordinates == null) {
                return false
            }

            return myCoordinates.subtract(otherCoordinates).norm() == 1
        }

        fun isOnTheSameCell(other: SpatialObject): Boolean {
            val myCoordinates = objectCoordinates[this]
            val otherCoordinates = objectCoordinates[other]

            if (myCoordinates == null || otherCoordinates == null) {
                return false
            }

            return myCoordinates == otherCoordinates
        }

        fun canBePutOn(coordinates: Vector2D): Boolean {
            return staticMap.isPassable(coordinates) &&
                objectsInCell[coordinates]?.map {
                    willStepOnTheSameCellWith(it)
                }?.all { it == true} ?: true
        }

        fun putOn(coordinates: Vector2D) {
            if (!canBePutOn(coordinates)) {
                throw InvalidCellToPutOnException()
            }

            val previousCoordinates = objectCoordinates[this]
            if (previousCoordinates != null) {
                objectsInCell[previousCoordinates]?.remove(this)
            }

            objectCoordinates[this] = coordinates
            objectsInCell.getOrPut(coordinates) { mutableSetOf() }.add(this)
        }

        fun canBeTranslatedOn(vector: Vector2D): Boolean {
            val coordinates = objectCoordinates[this] ?:
                throw ObjectWithNoPositionException()

            return canBePutOn(coordinates.add(vector))
        }

        fun translateOn(vector: Vector2D) {
            val coordinates = objectCoordinates[this] ?:
                throw ObjectWithNoPositionException()

            putOn(coordinates.add(vector))
        }

        fun objectsOnTheSameCell(): List<SpatialObject> {
            val coordinates = objectCoordinates[this] ?: throw ObjectWithNoPositionException()
            return objectsInCell[coordinates]!!.toList()
        }

        fun distanceTo(other: SpatialObject): Int {
            val myCoordinates = objectCoordinates[this]
            val otherCoordinates = objectCoordinates[other]

            if (myCoordinates == null || otherCoordinates == null) {
                throw ObjectWithNoPositionException()
            }

            return staticMap.distanceBetween(myCoordinates, otherCoordinates)
        }

        fun distanceDistributionTo(other: SpatialObject): Map<Direction, Int> {
            val myCoordinates = objectCoordinates[this]
            val otherCoordinates = objectCoordinates[other]

            if (myCoordinates == null || otherCoordinates == null) {
                throw ObjectWithNoPositionException()
            }

            return Direction.values().map {
                Pair(it, staticMap.distanceBetween(myCoordinates.add(it.vector), otherCoordinates))
            }.toMap()
        }

        override fun destroy() {
            super.destroy()

            val coordinates = objectCoordinates[this] ?: return

            objectCoordinates.remove(this)
            objectsInCell[coordinates]?.remove(this)
        }
    }
}