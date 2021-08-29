package de.ericmuench.diceapp.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import de.ericmuench.diceapp.R
import de.ericmuench.diceapp.util.MenuItem

@Composable
fun AppBar(title: String, menuItems: List<MenuItem> = listOf()){
    TopAppBar(
        title = { Text(title) },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            var dropDownExpanded by remember { mutableStateOf(false) }
            IconButton(onClick = { dropDownExpanded = !dropDownExpanded }) {
                Box() {
                    Icon(Icons.Filled.MoreVert, stringResource(id = R.string.more))
                    DropdownMenu(
                        expanded = dropDownExpanded,
                        onDismissRequest = { dropDownExpanded = false }
                    ) {
                        menuItems.forEach {
                            DropdownMenuItem(
                                onClick = {
                                    dropDownExpanded = false
                                    it.onClick.invoke(it)
                                }
                            ) {
                                Text(text = it.title)
                            }
                        }
                    }
                }
            }
        }
    )
}