package ru.spbau.bachelor2015.veselov.hw06.model

import ru.spbau.bachelor2015.veselov.hw06.model.objects.Exit
import ru.spbau.bachelor2015.veselov.hw06.model.objects.PlayerCharacter

interface SpatialObjectVisitor<R> {
    fun visit(player: PlayerCharacter): R

    fun visit(player: Exit): R
}
