package ru.spbau.bachelor2015.veselov.hw06.model.map

import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph
import org.jgrapht.traverse.BreadthFirstIterator
import ru.spbau.bachelor2015.veselov.hw06.model.Direction
import ru.spbau.bachelor2015.veselov.hw06.model.Vector2D

/**
 * A map that has rooms which are connected with a corridors.
 */
class RoomsCorridorsMap(
    roomsInColumn: Int,
    roomsInRow: Int,
    roomSize: Int,
    corridorLength: Int,
    corridorWidth: Int
) : StaticMap {
    private val passableCells: Set<Vector2D>

    private val graph = SimpleGraph<Vector2D, DefaultEdge>(DefaultEdge::class.java)

    init {
        val tmp = mutableSetOf<Vector2D>()

        fun addHorizontalCorridor(roomInitialX: Int, roomInitialY: Int) {
            val corridorInitialX = roomInitialX + roomSize
            val corridorInitialY = roomInitialY + (roomSize - corridorWidth) / 2
            for (x in corridorInitialX until (corridorInitialX + corridorLength)) {
                for (y in corridorInitialY until (corridorInitialY + corridorWidth)) {
                    tmp.add(Vector2D(x, y))
                }
            }
        }

        fun addVerticalCorridor(roomInitialX: Int, roomInitialY: Int) {
            val corridorInitialX = roomInitialX + (roomSize - corridorWidth) / 2
            val corridorInitialY = roomInitialY + roomSize
            for (x in corridorInitialX until (corridorInitialX + corridorWidth)) {
                for (y in corridorInitialY until (corridorInitialY + corridorLength)) {
                    tmp.add(Vector2D(x, y))
                }
            }
        }

        for (roomX in 0..(roomsInRow - 1)) {
            for (roomY in 0..(roomsInColumn - 1)) {
                val roomInitialX = roomX * (roomSize + corridorLength)
                val roomInitialY = roomY * (roomSize + corridorLength)
                for (x in roomInitialX until (roomInitialX + roomSize)) {
                    for (y in roomInitialY until (roomInitialY + roomSize)) {
                        tmp.add(Vector2D(x, y))
                    }
                }

                if (roomX != roomsInRow - 1) {
                    addHorizontalCorridor(roomInitialX, roomInitialY)
                }

                if (roomY != roomsInColumn - 1) {
                    addVerticalCorridor(roomInitialX, roomInitialY)
                }
            }
        }

        passableCells = tmp
    }

    init {
        for (coordinate in passableCells) {
            graph.addVertex(coordinate)
        }

        for (coordinate in passableCells) {
            for (direction in Direction.values()) {
                val nextCoordinate = coordinate.add(direction.vector)

                if (passableCells.contains(nextCoordinate)) {
                    graph.addEdge(coordinate, nextCoordinate)
                }
            }
        }
    }

    override fun isPassable(coordinates: Vector2D): Boolean {
        return passableCells.contains(coordinates)
    }

    override fun distanceBetween(coordinates1: Vector2D, coordinates2: Vector2D): Int {
        if (!isPassable(coordinates1) || !isPassable(coordinates2)) {
            throw IllegalArgumentException("Coordinates must be passable")
        }

        val iterator = BreadthFirstIterator(graph, coordinates1)
        for (vertex in iterator) {
            if (vertex == coordinates2) {
                return iterator.getDepth(vertex)
            }
        }

        throw IllegalStateException("Passable coordinates are not connected")
    }

    override fun uniformlyDistributedCells(amount: Int): List<Vector2D> {
        val allCells = passableCells.toMutableList()

        if (amount > allCells.size) {
            throw IllegalArgumentException("Too many cells requested")
        }

        allCells.shuffle()
        return allCells.take(amount)
    }

    override fun getNumberOfCells(): Int {
        return passableCells.size
    }
}
