package ru.spbau.bachelor2015.veselov.hw06.model

import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.SpatialObjectView
import ru.spbau.bachelor2015.veselov.hw06.model.map.StaticMap

class InvalidCellToPutOnException: Exception()

class ObjectWithNoPositionException: Exception()

class SpaceManager(
    private val gameObjectsManager: GameObjectsManager,
    val staticMap: StaticMap
) {
    private val objectCoordinates = mutableMapOf<SpatialObject, Vector2D>()

    private val objectsInCell = mutableMapOf<Vector2D, MutableSet<SpatialObject>>()

    abstract class SpatialObject(
        private val spaceManager: SpaceManager,
        priority: GameObjectPriority
    ): GameObjectsManager.GameObject(spaceManager.gameObjectsManager, priority), SpatialObjectView {
        abstract fun willStepOnTheSameCellWith(other: SpatialObject): Boolean

        abstract fun <R> accept(visitor: SpatialObjectVisitor<R>): R

        override fun isPassableRelatively(vector: Vector2D): Boolean {
            val coordinates = spaceManager.objectCoordinates[this] ?:
                throw ObjectWithNoPositionException()

            return spaceManager.staticMap.isPassable(coordinates.add(vector))
        }

        override fun getObjectsRelatively(vector: Vector2D): List<SpatialObjectView> {
            val coordinates = spaceManager.objectCoordinates[this] ?:
                throw ObjectWithNoPositionException()

            return spaceManager.objectsInCell[coordinates.add(vector)]?.toList() ?: emptyList()
        }

        fun isInAdjacentCell(other: SpatialObject): Boolean {
            val myCoordinates = spaceManager.objectCoordinates[this]
            val otherCoordinates = spaceManager.objectCoordinates[other]

            if (myCoordinates == null || otherCoordinates == null) {
                return false
            }

            return myCoordinates.subtract(otherCoordinates).norm() == 1
        }

        fun isOnTheSameCell(other: SpatialObject): Boolean {
            val myCoordinates = spaceManager.objectCoordinates[this]
            val otherCoordinates = spaceManager.objectCoordinates[other]

            if (myCoordinates == null || otherCoordinates == null) {
                return false
            }

            return myCoordinates == otherCoordinates
        }

        fun canBePutOn(coordinates: Vector2D): Boolean {
            return spaceManager.staticMap.isPassable(coordinates) &&
                spaceManager.objectsInCell[coordinates]?.map {
                    willStepOnTheSameCellWith(it)
                }?.all { it == true } ?: true
        }

        fun putOn(coordinates: Vector2D) {
            if (!canBePutOn(coordinates)) {
                throw InvalidCellToPutOnException()
            }

            val previousCoordinates = spaceManager.objectCoordinates[this]
            if (previousCoordinates != null) {
                spaceManager.objectsInCell[previousCoordinates]?.remove(this)
            }

            spaceManager.objectCoordinates[this] = coordinates
            spaceManager.objectsInCell.getOrPut(coordinates) { mutableSetOf() }.add(this)
        }

        fun canBeTranslatedOn(vector: Vector2D): Boolean {
            val coordinates = spaceManager.objectCoordinates[this] ?:
                throw ObjectWithNoPositionException()

            return canBePutOn(coordinates.add(vector))
        }

        fun translateOn(vector: Vector2D) {
            val coordinates = spaceManager.objectCoordinates[this] ?:
                throw ObjectWithNoPositionException()

            putOn(coordinates.add(vector))
        }

        fun objectsOnTheSameCell(): List<SpatialObject> {
            val coordinates = spaceManager.objectCoordinates[this] ?:
                throw ObjectWithNoPositionException()

            return spaceManager.objectsInCell[coordinates]!!.toList()
        }

        fun distanceTo(other: SpatialObject): Int {
            val myCoordinates = spaceManager.objectCoordinates[this]
            val otherCoordinates = spaceManager.objectCoordinates[other]

            if (myCoordinates == null || otherCoordinates == null) {
                throw ObjectWithNoPositionException()
            }

            return spaceManager.staticMap.distanceBetween(myCoordinates, otherCoordinates)
        }

        fun distanceDistributionTo(other: SpatialObject): Map<Direction, Int> {
            val myCoordinates = spaceManager.objectCoordinates[this]
            val otherCoordinates = spaceManager.objectCoordinates[other]

            if (myCoordinates == null || otherCoordinates == null) {
                throw ObjectWithNoPositionException()
            }

            return Direction.values().map {
                Pair(it, spaceManager.staticMap.distanceBetween(
                    myCoordinates.add(it.vector),
                    otherCoordinates
                ))
            }.toMap()
        }

        override fun destroy() {
            super.destroy()

            val coordinates = spaceManager.objectCoordinates[this] ?: return

            spaceManager.objectCoordinates.remove(this)
            spaceManager.objectsInCell[coordinates]?.remove(this)
        }
    }
}
