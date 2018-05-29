package ru.spbau.bachelor2015.veselov.hw06.model.objects

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Test
import ru.spbau.bachelor2015.veselov.hw06.model.GameObjectsManager
import ru.spbau.bachelor2015.veselov.hw06.model.SpaceManager
import ru.spbau.bachelor2015.veselov.hw06.model.Vector2D
import ru.spbau.bachelor2015.veselov.hw06.model.map.RectangularMap

class BigRatTest {
    @Test
    fun aggressivenessTest() {
        val gameObjectsManager = GameObjectsManager()
        val spaceManager = SpaceManager(gameObjectsManager, RectangularMap(5, 5))

        val player = PlayerCharacter(spaceManager)
        player.putOn(Vector2D(2, 1))

        val monsterAreaCentre = MonsterAreaCentre(spaceManager)
        monsterAreaCentre.putOn(Vector2D(2, 2))

        val bigRat = BigRat(spaceManager, monsterAreaCentre, 3, player)
        bigRat.putOn(Vector2D(2, 2))

        for (i in 0..100) {
            gameObjectsManager.makeStep()
        }

        assertThat(player.isDead(), `is`(equalTo(true)))
    }
}