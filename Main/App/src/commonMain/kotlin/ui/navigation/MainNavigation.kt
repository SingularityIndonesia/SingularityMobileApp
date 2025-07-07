package ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.screen.about.AboutScreen
import ui.screen.account.AccountSettingScreen
import ui.screen.home.HomeScreen
import ui.screen.notification.NotificationSettingScreen
import ui.screen.security.SecuritySettingScreen
import ui.screen.support.HelpAndSupportScreen

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
            route = HomeDestination,
            arguments = listOf(
                navArgument("section") {
                    defaultValue = HomeSection.Colors.name
                    nullable = true
                }
            ),
        ) {
            HomeScreen()
        }

        composable(
            route = AboutDestination,
        ) {
            AboutScreen()
        }

        composable(
            route = SecuritySettingDestination,
        ) {
            SecuritySettingScreen()
        }

        composable(
            route = HelpAndSupportDestination,
        ) {
            HelpAndSupportScreen()
        }

        composable(
            route = AccountSettingDestination
        ) {
            AccountSettingScreen()
        }

        composable(
            route = NotificationSettingDestination
        ) {
            NotificationSettingScreen()
        }
    }
}