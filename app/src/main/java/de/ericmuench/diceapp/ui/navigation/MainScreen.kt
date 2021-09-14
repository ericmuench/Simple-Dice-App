package de.ericmuench.diceapp.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import de.ericmuench.diceapp.R
import de.ericmuench.diceapp.model.DiceDisplayMode
import de.ericmuench.diceapp.ui.composables.AppBar
import de.ericmuench.diceapp.ui.composables.ClassicDiceView
import de.ericmuench.diceapp.ui.composables.LoadingContent
import de.ericmuench.diceapp.ui.composables.SimpleDiceView
import de.ericmuench.diceapp.util.MenuItem
import de.ericmuench.diceapp.util.MenuItemMode
import de.ericmuench.diceapp.util.navigate
import de.ericmuench.diceapp.viewmodel.MainViewModel

@ExperimentalAnimationApi
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
){
    val displayNumber : Int by viewModel.number.collectAsState(initial = 1)
    val displayMode = viewModel.diceDisplayMode.observeAsState()
    MainScreenContent(displayMode.value, displayNumber, viewModel::nextValue, navController)
}

@ExperimentalAnimationApi
@Composable
fun MainScreenContent(
    displayMode: DiceDisplayMode?,
    displayNumber: Int,
    onDice: () -> Unit,
    navController: NavController? = null
){
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
            when(displayMode){
                DiceDisplayMode.CLASSIC -> ClassicDiceView(displayNumber, onClick = onDice)
                DiceDisplayMode.SIMPLE -> SimpleDiceView(
                    displayNumber = displayNumber,
                    onClick = onDice
                )
                else -> LoadingContent()
            }

        }
    }
}