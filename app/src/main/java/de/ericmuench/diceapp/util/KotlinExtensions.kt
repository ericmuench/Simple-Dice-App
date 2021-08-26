package de.ericmuench.diceapp.util

fun Int.isOdd() = !this.isEven()

fun Int.isEven() = this % 2 == 0