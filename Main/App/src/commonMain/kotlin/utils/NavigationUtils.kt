package utils

import androidx.compose.runtime.*
import androidx.core.bundle.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController

@Composable
fun currentDestination(navController: NavHostController): State<NavDestination?> {
    val destination = remember { mutableStateOf<NavDestination?>(null) }

    DisposableEffect(navController) {
        val destinationChangedListener = { c: NavController, d: NavDestination, arg: Bundle? ->
            destination.value = d
        }

        navController.addOnDestinationChangedListener(destinationChangedListener)

        onDispose {
            navController.removeOnDestinationChangedListener(destinationChangedListener)
        }
    }

    return destination
}

@Composable
fun currentDestinationArgument(navController: NavHostController): State<Bundle?> {
    val argument = remember { mutableStateOf<Bundle?>(null) }

    DisposableEffect(navController) {
        val destinationChangedListener = { c: NavController, d: NavDestination, arg: Bundle? ->
            argument.value = arg
        }

        navController.addOnDestinationChangedListener(destinationChangedListener)

        onDispose {
            navController.removeOnDestinationChangedListener(destinationChangedListener)
        }
    }

    return argument
}