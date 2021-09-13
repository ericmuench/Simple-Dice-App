package de.ericmuench.diceapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import de.ericmuench.diceapp.R
import de.ericmuench.diceapp.util.MenuItem
import de.ericmuench.diceapp.util.MenuItemMode

@Composable
fun AppBar(
    title: String,
    menuItems: List<MenuItem> = listOf(),
    defaultIconSpace: Int = 3,
    menuExpanded : MutableState<Boolean> = remember { mutableStateOf(false) }
){
    TopAppBar(
        title = { Text(title) },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {

            val (actionItems, overflowItems) = remember {
                separateIntoShowAndOverflow(menuItems, defaultIconSpace)
            }

            //always displayed items
            actionItems.forEach {
                AppBarIcon(
                    title = it.title,
                    icon = it.icon,
                    onClick = { it.onClick.invoke(it) },
                    displayTitleUnderIcon = it.displayTitleUnderIcon
                )
            }

            //overflow items
            if(overflowItems.isNotEmpty()){
                IconButton(onClick = { menuExpanded.value = true }) {
                    Box {
                        Icon(Icons.Filled.MoreVert, stringResource(id = R.string.more))
                    }

                    DropdownMenu(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        expanded = menuExpanded.value,
                        onDismissRequest = { menuExpanded.value = false }
                    ) {
                        overflowItems.forEach {
                            DropdownMenuItem(
                                onClick = {
                                    menuExpanded.value = false
                                    it.onClick.invoke(it)
                                },
                                modifier = Modifier
                            ) {
                                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                    if(it.icon != null){
                                        Icon(it.icon, contentDescription = it.title)
                                    }
                                    Text(text = it.title, Modifier.padding(horizontal = 4.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun AppBarIcon(
    title: String,
    icon: ImageVector?,
    onClick: () -> Unit,
    displayTitleUnderIcon: Boolean
){
    if(icon != null){
        IconButton(
            onClick = {
                onClick.invoke()
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(icon, title)
                if(displayTitleUnderIcon){
                    Text(title, fontSize = 14.sp, softWrap = false, overflow = TextOverflow.Visible)
                }
            }
        }
    }
    else{
        Text(title)
    }
}

// this function was implemented using the following Github-Code:
// https://gist.github.com/MachFour/369ebb56a66e2f583ebfb988dda2decf
private fun separateIntoShowAndOverflow(
    menuItems: List<MenuItem>,
    defaultIconSpace: Int
) : Pair<List<MenuItem>, List<MenuItem>>{
    //show modes
    val showModes = menuItems.groupBy { it.itemMode }
    val showModeCounts = showModes.mapValues { it.value.size }
    val (alwaysCnt, neverCnt, ifRoomCnt) = Triple(
        showModeCounts[MenuItemMode.ALWAYS] ?: 0,
        showModeCounts[MenuItemMode.NEVER] ?: 0,
        showModeCounts[MenuItemMode.IF_ROOM] ?: 0
    )

    val needsOverflow = alwaysCnt + ifRoomCnt > defaultIconSpace || neverCnt > 0
    val actionIconSpace = defaultIconSpace - (if (needsOverflow) 1 else 0)

    var ifRoomToDisplay = actionIconSpace - alwaysCnt

    val actionItems = mutableListOf<MenuItem>()
    val overflowItems = mutableListOf<MenuItem>()

    menuItems.forEach {
        when(it.itemMode){
            MenuItemMode.ALWAYS -> {
                actionItems.add(it)
            }
            MenuItemMode.NEVER -> {
                overflowItems.add(it)
            }
            MenuItemMode.IF_ROOM -> {
                if(ifRoomToDisplay > 0){
                    actionItems.add(it)
                    ifRoomToDisplay--
                }
                else{
                    overflowItems.add(it)
                }
            }
        }
    }

    return Pair(actionItems, overflowItems)
}