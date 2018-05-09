package ru.spbau.bachelor2015.veselov.hw06.model

enum class GameObjectPriority {
    LEAST, MONSTER, FIRE_WALL, STATIC_OBJECT;
}

abstract class GameObjectsManager {
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

    fun makeStep() {
        gameObjects.forEach {
            it.makeStep()
        }
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
            gameObjectsManager.gameObjects.remove(this)
        }
    }
}
