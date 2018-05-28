package ru.spbau.bachelor2015.veselov.hw06.model

import ru.spbau.bachelor2015.veselov.hw06.model.objects.BigRat
import ru.spbau.bachelor2015.veselov.hw06.model.objects.PlayerCharacter

interface BattleUnitVisitor<R> {
    fun visit(player: PlayerCharacter): R

    fun visit(bigRat: BigRat): R
}
