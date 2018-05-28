package ru.spbau.bachelor2015.veselov.hw06.model.objects

import ru.spbau.bachelor2015.veselov.hw06.model.GameObjectPriority
import ru.spbau.bachelor2015.veselov.hw06.model.SpaceManager
import ru.spbau.bachelor2015.veselov.hw06.model.`interface`.MonsterView

abstract class Monster(
    spaceManager: SpaceManager,
    priority: GameObjectPriority,
    attributes: Map<Attribute, Int>
) : BattleUnit(
    spaceManager,
    priority,
    attributes
), MonsterView
