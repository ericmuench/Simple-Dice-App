package de.ericmuench.diceapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.ericmuench.diceapp.ui.composables.*
import de.ericmuench.diceapp.ui.theme.DiceAppTheme
import de.ericmuench.diceapp.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import de.ericmuench.diceapp.ui.navigation.*
import de.ericmuench.diceapp.util.MenuItem
import de.ericmuench.diceapp.util.MenuItemMode
import de.ericmuench.diceapp.viewmodel.SettingsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceAppTheme {
                val startRoute = Route(AppRoutes.MAIN) { MainScreen(it) }
                val routes = listOf<Route>(
                    startRoute,
                    Route(AppRoutes.SETTINGS) {
                        //val vm : SettingsViewModel by viewModels()
                        SettingsScreen(
                            navController = it,
                            //viewModel = vm
                        )
                    }
                )

                AppNavigation(routes = routes, startRoute = startRoute)
            }
        }
    }
}






@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreenContent(displayNumber = 6, {})
}