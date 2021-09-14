package de.ericmuench.diceapp.ui.navigation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.ericmuench.diceapp.R
import de.ericmuench.diceapp.model.DiceDisplayMode
import de.ericmuench.diceapp.ui.composables.LoadingContent
import de.ericmuench.diceapp.ui.composables.RadioGroup
import de.ericmuench.diceapp.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel : SettingsViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.settings)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.goback)
                        )
                    }
                }
            )
        }
    ) {
        val diceDisplayMode = viewModel.diceDisplayMode.observeAsState()

        if(diceDisplayMode.value != null){
            Column(Modifier.padding(8.dp)) {
                Text(stringResource(id = R.string.setting_displaymode))
                val displayModes = DiceDisplayMode.values().toList()
                RadioGroup<DiceDisplayMode>(
                    items = displayModes,
                    selectedItem = diceDisplayMode.value!!,
                    onSelectionChanged = {
                        viewModel.setDiceDisplayMode(it)
                    },
                    itemTitle = {
                        Text(stringResource(id = it.titleId))
                    }
                )
            }
        }
        else{
            LoadingContent()
        }



        SideEffect {
            viewModel.loadSettings()
        }

    }
}