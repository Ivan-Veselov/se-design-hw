package ru.spbau.bachelor2015.veselov.hw06.model

enum class GameObjectPriority {
    LEAST, MONSTER, FIRE_WALL, STATIC_OBJECT;
}

/**
 * Manager that stores all game objects that are aware of steps and have some position on a map.
 */
class GameObjectsManager {
    private val gameObjects = sortedSetOf<GameObject>(Comparator { o1, o2 ->
        if (o1.priority < o2.priority) {
            return@Comparator 1
        }

        if (o1.priority > o2.priority) {
            return@Comparator -1
        }

        if (o1.hashCode() < o2.hashCode()) {
            return@Comparator 1
        }

        if (o1.hashCode() > o2.hashCode()) {
            return@Comparator -1
        }

        return@Comparator 0
    })

    private val destroyedObjects = mutableSetOf<GameObject>()

    val log = GameLog()

    /**
     * Makes a game turn by allowing all objects to execute their logic.
     */
    fun makeStep() {
        gameObjects.forEach {
            if (!destroyedObjects.contains(it)) {
                it.makeStep()
            }
        }

        destroyedObjects.forEach {
            gameObjects.remove(it)
        }

        destroyedObjects.clear()
    }

    abstract class GameObject(
        private val gameObjectsManager: GameObjectsManager,
        val priority: GameObjectPriority
    ) {
        init {
            gameObjectsManager.gameObjects.add(this)
        }

        abstract fun makeStep()

        /**
         * Destroys this object. From this moment it will never receive notification about new
         * turns.
         */
        open fun destroy() {
            gameObjectsManager.destroyedObjects.add(this)
        }

        protected fun getLog(): GameLog {
            return gameObjectsManager.log
        }
    }
}
