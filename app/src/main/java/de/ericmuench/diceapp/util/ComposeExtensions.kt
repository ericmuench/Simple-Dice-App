package de.ericmuench.diceapp.util

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import de.ericmuench.diceapp.ui.navigation.AppRouteId

fun NavController.navigate(
    route: AppRouteId,
    builder: NavOptionsBuilder.() -> Unit = {  }
) = navigate(route.title, builder)