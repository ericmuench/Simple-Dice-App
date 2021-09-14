package de.ericmuench.diceapp.model

import androidx.compose.ui.res.stringResource
import de.ericmuench.diceapp.R

enum class DiceDisplayMode(val titleId: Int) {
    SIMPLE(R.string.simple),
    CLASSIC(R.string.classic)
}