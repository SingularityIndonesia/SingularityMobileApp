/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import main.home.presentation.HomeScreen
import main.home.presentation.HomeScreenPLD
import system.core.lifecycle.SaveAbleState


@Composable
fun MainNavigation() {
    val saveAbleState = remember { SaveAbleState() }
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable(
            route = "home",
        ) { backstackEntry ->
            val pld = remember {
                HomeScreenPLD()
            }
            HomeScreen(
                pld = pld,
                saveAbleState = saveAbleState
            )
        }
    }
}