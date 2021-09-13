package de.ericmuench.diceapp.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

typealias Route = Pair<AppRouteId, @Composable (NavController) -> Unit>

@ExperimentalAnimationApi
@Composable
fun AppNavigation(
    routes : List<Route>,
    startRoute: Route
){
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = startRoute.first.title){
        routes.forEach { r ->
           composable(
               r.first.title,
               enterTransition = {_,_ ->
                   expandIn(Alignment.CenterStart)
               }
           ){
               r.second.invoke(navController)
           }
        }
    }
}

enum class AppRouteId(val title: String){
    MAIN("main"),
    SETTINGS("settings")
}

