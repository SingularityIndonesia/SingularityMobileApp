package ui.navigation

import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
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
        startDestination = HomeDestination,
        enterTransition = { slideIn { IntOffset(x = it.width, y = 0) } },
        popEnterTransition = { slideIn { IntOffset(x = -it.width, y = 0) } },
        exitTransition = { slideOut { IntOffset(x = -it.width, y = 0) } },
        popExitTransition = { slideOut { IntOffset(x = it.width, y = 0) } },
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
            deepLinks = listOf(
                navDeepLink { uriPattern = AboutDeepLink },
                navDeepLink { uriPattern = AboutCustomDeepLink }
            )
        ) {
            AboutScreen()
        }

        composable(
            route = SecuritySettingDestination,
            deepLinks = listOf(
                navDeepLink { uriPattern = SecuritySettingDeepLink },
                navDeepLink { uriPattern = SecuritySettingCustomDeepLink }
            )
        ) {
            SecuritySettingScreen()
        }

        composable(
            route = HelpAndSupportDestination,
            deepLinks = listOf(
                navDeepLink { uriPattern = HelpAndSupportDeepLink },
                navDeepLink { uriPattern = HelpAndSupportCustomDeepLink }
            )
        ) {
            HelpAndSupportScreen()
        }

        composable(
            route = AccountSettingDestination,
            deepLinks = listOf(
                navDeepLink { uriPattern = AccountSettingDeepLink },
                navDeepLink { uriPattern = AccountSettingCustomDeepLink }
            )
        ) {
            AccountSettingScreen()
        }

        composable(
            route = NotificationSettingDestination,
            deepLinks = listOf(
                navDeepLink { uriPattern = NotificationSettingDeepLink },
                navDeepLink { uriPattern = NotificationSettingCustomDeepLink }
            )
        ) {
            NotificationSettingScreen()
        }
    }
}
