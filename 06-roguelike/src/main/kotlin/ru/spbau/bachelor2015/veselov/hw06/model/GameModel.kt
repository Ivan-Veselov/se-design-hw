package ru.spbau.bachelor2015.veselov.hw06.model

import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.PlayerCharacterView
import ru.spbau.bachelor2015.veselov.hw06.model.map.RoomsCorridorsMap
import ru.spbau.bachelor2015.veselov.hw06.model.objects.Exit
import ru.spbau.bachelor2015.veselov.hw06.model.objects.PlayerCharacter

class GameModel {
    private val gameObjectsManager = GameObjectsManager()

    private val spaceManager = SpaceManager(
        gameObjectsManager,
            RoomsCorridorsMap(3, 3, 6, 5, 2)
    )

    private val playerCharacter = PlayerCharacter(spaceManager)

    init {
        val cells = spaceManager.staticMap.uniformlyDistributedCells(2)
        playerCharacter.putOn(cells[0])
        Exit(spaceManager).putOn(cells[1])
    }

    fun getPlayer(): PlayerCharacterView {
        return playerCharacter
    }

    fun makePlayerAction(direction: Direction) {
        if (playerCharacter.canBeTranslatedOn(direction.vector)) {
            playerCharacter.translateOn(direction.vector)
            return
        }
    }
}
