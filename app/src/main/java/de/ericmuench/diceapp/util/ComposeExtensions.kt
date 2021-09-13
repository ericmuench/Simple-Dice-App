package de.ericmuench.diceapp.util

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import de.ericmuench.diceapp.ui.navigation.AppRoutes

fun NavController.navigate(
    route: AppRoutes,
    builder: NavOptionsBuilder.() -> Unit = {  }
) = navigate(route.title, builder)