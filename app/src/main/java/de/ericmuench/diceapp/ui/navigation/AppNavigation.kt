package de.ericmuench.diceapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

typealias Route = Pair<AppRoutes, @Composable (NavController) -> Unit>

@Composable
fun AppNavigation(
    routes : List<Route>,
    startRoute: Route
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startRoute.first.title){
        routes.forEach { r ->
           composable(r.first.title){
               r.second.invoke(navController)
           }
        }
    }
}

enum class AppRoutes(val title: String){
    MAIN("main"),
    SETTINGS("settings")
}