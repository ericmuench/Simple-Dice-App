package de.ericmuench.diceapp.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.ericmuench.diceapp.R

@Composable
fun SimpleDiceView(
    displayNumber: Int,
    modifier : Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier
            .clickable(onClick = onClick, interactionSource = interactionSource, indication = null)
    ) {
        Text(
            "$displayNumber",
            fontSize = 96.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Text(text = stringResource(id = R.string.tap_to_generate_new_number),
            fontWeight = FontWeight.Thin,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )
    }

}