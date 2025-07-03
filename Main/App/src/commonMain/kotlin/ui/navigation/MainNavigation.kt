package ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.screen.home.HomeScreen

@Preview
@Composable
fun MainNavigation(
    controller: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = controller,
        startDestination = HomeDestination
    ) {
        composable(
            route = "home?section={section}",
            arguments = listOf(
                navArgument("section") {
                    defaultValue = HomeSection.Colors.name
                    nullable = true
                }
            ),
        ) {
            HomeScreen()
        }
    }
}