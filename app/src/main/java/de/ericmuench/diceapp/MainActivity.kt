package de.ericmuench.diceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import de.ericmuench.diceapp.ui.theme.DiceAppTheme
import dagger.hilt.android.AndroidEntryPoint
import de.ericmuench.diceapp.ui.navigation.*

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceAppTheme {
                val startRoute = Route(AppRouteId.MAIN) { MainScreen(it) }
                val routes = listOf<Route>(
                    startRoute,
                    Route(AppRouteId.SETTINGS) {
                        SettingsScreen(
                            navController = it,
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