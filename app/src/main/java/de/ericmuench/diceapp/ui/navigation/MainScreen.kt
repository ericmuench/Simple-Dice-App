package de.ericmuench.diceapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import de.ericmuench.diceapp.R
import de.ericmuench.diceapp.ui.composables.AppBar
import de.ericmuench.diceapp.ui.composables.ClassicDiceView
import de.ericmuench.diceapp.util.MenuItem
import de.ericmuench.diceapp.util.MenuItemMode
import de.ericmuench.diceapp.util.navigate
import de.ericmuench.diceapp.viewmodel.MainViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
){
    val displayNumber : Int by viewModel.number.collectAsState(initial = 1)
    MainScreenContent(displayNumber, viewModel::nextValue, navController)
}

@Composable
fun MainScreenContent(displayNumber: Int, onDice: () -> Unit, navController: NavController? = null){
    //val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(id = R.string.app_name),
                menuItems = listOf(
                    MenuItem(
                        title = stringResource(id = R.string.settings),
                        icon = Icons.Default.Settings,
                        displayTitleUnderIcon = false,
                        itemMode = MenuItemMode.NEVER,
                        onClick = {
                            navController?.navigate(AppRouteId.SETTINGS)
                        }
                    )
                )
            )
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize()
        ) {
            ClassicDiceView(displayNumber, onClick = onDice)
        }
    }
}