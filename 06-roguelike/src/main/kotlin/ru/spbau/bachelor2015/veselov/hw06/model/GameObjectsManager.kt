package ru.spbau.bachelor2015.veselov.hw06.model

enum class GameObjectPriority {
    LEAST, MONSTER, FIRE_WALL, STATIC_OBJECT;
}

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

        open fun destroy() {
            gameObjectsManager.destroyedObjects.add(this)
        }

        protected fun getLog(): GameLog {
            return gameObjectsManager.log
        }
    }
}