package ru.spbau.bachelor2015.veselov.hw06.model

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Test
import ru.spbau.bachelor2015.veselov.hw06.model.map.RoomsCorridorsMap

class RoomsCorridorsMapTest {
    @Test
    fun testDistance() {
        // .......
        // .  .  .
        // .  .  .
        // ..f....
        // .  .  .
        // .  .  .
        // .s.....
        val map = RoomsCorridorsMap(
                3,
                3,
                1,
                2,
                1
        )

        assertThat(
            map.distanceBetween(Vector2D(1, 0), Vector2D(2, 3)),
            `is`(equalTo(4))
        )
    }
}