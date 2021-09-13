package de.ericmuench.diceapp.util


import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem (
    val title: String,
    var onClick : (MenuItem) -> Unit,
    val icon : ImageVector? = null,
    val itemMode : MenuItemMode = MenuItemMode.IF_ROOM,
    val displayTitleUnderIcon : Boolean = false
)

enum class MenuItemMode{
    ALWAYS,
    IF_ROOM,
    NEVER
}