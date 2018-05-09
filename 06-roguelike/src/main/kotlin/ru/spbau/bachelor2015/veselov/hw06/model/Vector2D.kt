package ru.spbau.bachelor2015.veselov.hw06.model

data class Vector2D(val x: Int, val y: Int) {
    fun add(other: Vector2D): Vector2D {
        return Vector2D(x + other.x, y + other.y)
    }

    fun subtract(other: Vector2D): Vector2D {
        return Vector2D(x - other.x, y - other.y)
    }

    fun norm(): Int {
        return Math.abs(x) + Math.abs(y)
    }
}

enum class Direction(val vector: Vector2D) {
    NORTH(Vector2D(0, 1)),
    NORTHEAST(Vector2D(1, 1)),
    EAST(Vector2D(1, 0)),
    SOUTHEAST(Vector2D(1, -1)),
    SOUTH(Vector2D(0, -1)),
    SOUTHWEST((Vector2D(-1, -1))),
    WEST((Vector2D(-1, 0))),
    NORTHWEST((Vector2D(-1, 1)));
}
