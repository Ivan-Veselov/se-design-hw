package ru.spbau.bachelor2015.veselov.hw06.model

import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.PlayerCharacterView
import ru.spbau.bachelor2015.veselov.hw06.model.objects.PlayerCharacter

class GameModel {
    private val gameObjectsManager = GameObjectsManager()

    private val spaceManager = SpaceManager(gameObjectsManager, RectangularMap(20, 20))

    private val playerCharacter = PlayerCharacter(spaceManager)

    init {
        playerCharacter.putOn(spaceManager.staticMap.uniformlyDistributedCells(1).first())
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
