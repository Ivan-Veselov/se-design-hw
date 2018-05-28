package ru.spbau.bachelor2015.veselov.hw06.model

import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.PlayerCharacterView
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.UsableItemView
import ru.spbau.bachelor2015.veselov.hw06.model.map.RoomsCorridorsMap
import ru.spbau.bachelor2015.veselov.hw06.model.objects.*
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.ItemApplier

class GameIsOverException : Exception()

class GameModel {
    private val gameObjectsManager = GameObjectsManager()

    private val spaceManager = SpaceManager(
        gameObjectsManager,
            RoomsCorridorsMap(3, 3, 6, 5, 2)
    )

    private val playerCharacter = PlayerCharacter(spaceManager)

    private val exit = Exit(spaceManager)

    init {
        val cellsInMaze = spaceManager.staticMap.getNumberOfCells()
        val numberOfMonsters = cellsInMaze * 2 / 100
        val numberOfChests = cellsInMaze * 2 / 100

        val cells = spaceManager.staticMap.uniformlyDistributedCells(
            numberOfChests + numberOfMonsters + 2
        )

        playerCharacter.putOn(cells[0])
        exit.putOn(cells[1])

        cells.drop(2).take(numberOfChests).forEach {
            val chest = Chest(spaceManager, playerCharacter)
            chest.putOn(it)
        }

        cells.drop(2 + numberOfChests).take(numberOfMonsters).forEach {
            val centre = MonsterAreaCentre(spaceManager)
            centre.putOn(it)

            val monster = BigRat(spaceManager, centre, 3, playerCharacter)
            monster.putOn(it)
        }
    }

    fun getPlayer(): PlayerCharacterView {
        return playerCharacter
    }

    fun makePlayerAction(direction: Direction) {
        if (isWon()) {
            throw GameIsOverException()
        }

        if (playerCharacter.canBeTranslatedOn(direction.vector)) {
            playerCharacter.translateOn(direction.vector)

            if (!isWon()) {
                gameObjectsManager.makeStep()
            }

            return
        }

        val monsters = playerCharacter.getObjectsRelatively(direction.vector)
                                      .filter { it is Monster }

        if (monsters.isNotEmpty()) {
            monsters.forEach {
                playerCharacter.attack(it as Monster)
            }

            gameObjectsManager.makeStep()
        }
    }

    fun useItem(item: UsableItemView) {
        ItemApplier.useItemOn(playerCharacter, item)
    }

    fun isWon(): Boolean {
        return exit.objectsOnTheSameCell().contains(playerCharacter)
    }

    fun isLost(): Boolean {
        return playerCharacter.isDead()
    }

    fun getLog(): GameLog {
        return gameObjectsManager.log
    }
}
