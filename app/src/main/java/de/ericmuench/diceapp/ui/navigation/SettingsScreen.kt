package de.ericmuench.diceapp.ui.navigation


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.ericmuench.diceapp.R
import de.ericmuench.diceapp.viewmodel.SettingsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint

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
        Text(viewModel.testData.value)
    }
}