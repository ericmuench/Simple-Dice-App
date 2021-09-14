package de.ericmuench.diceapp.ui.composables

import android.widget.RadioGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun <T : Any> RadioGroup(
    items: List<T>,
    selectedItem: T,
    onSelectionChanged: (T) -> Unit = {},
    itemTitle: @Composable (T) -> Unit = { Text(it.toString()) }
){
    Column {
        val selectedIndex = items.indexOf(selectedItem)
        for(idx in items.indices){
            Row {
                RadioButton(
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary),
                    selected = idx == selectedIndex,
                    onClick = {
                        onSelectionChanged.invoke(items[idx])
                    }
                )
                itemTitle.invoke(items[idx])
            }
        }
    }
}