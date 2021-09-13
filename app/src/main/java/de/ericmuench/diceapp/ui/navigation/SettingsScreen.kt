package de.ericmuench.diceapp.ui.navigation


import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.ericmuench.diceapp.R
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
        val testVal = viewModel.diceDisplayMode.observeAsState()
        Column {
            Text(testVal.value?.title ?: "no value")
            RadioGroup<String>(items = listOf("Hello", "World", "1234"))
        }

        LaunchedEffect(key1 = true){
            viewModel.loadSettings()
        }

    }
}