package ru.spbau.bachelor2015.veselov.hw06.model

import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.PlayerCharacterView
import ru.spbau.bachelor2015.veselov.hw06.model.map.RoomsCorridorsMap
import ru.spbau.bachelor2015.veselov.hw06.model.objects.*
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.ItemApplier
import ru.spbau.bachelor2015.veselov.hw06.model.objects.items.UsableItem

class GameIsOverException : Exception()

/**
 * This class represents a game and provides an interface to communicate with a game through.
 */
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

    /**
     * Returns a player character view.
     */
    fun getPlayer(): PlayerCharacterView {
        return playerCharacter
    }

    /**
     * Makes an action by a player in a given direction.
     */
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

    /**
     * Uses given usable item on player.
     */
    fun useItem(item: UsableItem) {
        ItemApplier.useItemOn(playerCharacter, item)
    }

    /**
     * Returns true if player has reached the exit.
     */
    fun isWon(): Boolean {
        return exit.objectsOnTheSameCell().contains(playerCharacter)
    }

    /**
     * Returns true if player is dead.
     */
    fun isLost(): Boolean {
        return playerCharacter.isDead()
    }

    /**
     * Returns game log.
     */
    fun getLog(): GameLog {
        return gameObjectsManager.log
    }
}
