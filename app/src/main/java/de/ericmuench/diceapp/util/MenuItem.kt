package de.ericmuench.diceapp.util

data class MenuItem (
    val title: String,
    var onClick : (MenuItem) -> Unit,
)