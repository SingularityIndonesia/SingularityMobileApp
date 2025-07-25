package ui.navigation

import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
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
import ui.screen.login.LoginScreen
import ui.screen.notification.NotificationSettingScreen
import ui.screen.otp.OtpScreen
import ui.screen.poet.PoetScreen
import ui.screen.security.SecuritySettingScreen
import ui.screen.support.HelpAndSupportScreen

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun MainNavigation(
    controller: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    NavHost(
        modifier = modifier,
        navController = controller,
        startDestination = Route.HomeDestination,
        enterTransition = { slideIn { IntOffset(x = it.width, y = 0) } },
        popEnterTransition = { slideIn { IntOffset(x = -it.width, y = 0) } },
        exitTransition = { slideOut { IntOffset(x = -it.width, y = 0) } },
        popExitTransition = { slideOut { IntOffset(x = it.width, y = 0) } },
    ) {
        composable(
            route = Route.LoginDestination
        ) {
            LoginScreen(
                goToOtpVerification = { email ->
                    controller.navigate(
                        Route.OtpVerificationDestinationBuilder(
                            Route.OtpPurpose.LOGIN_VERIFICATION,
                            email
                        )
                    )
                }
            )
        }

        composable(
            route = Route.PoetDestination
        ) {
            PoetScreen(
                onNavigateBack = {
                    controller.popBackStack()
                }
            )
        }

        composable(
            route = Route.OtpVerificationDestination,
            arguments = listOf(
                navArgument("purpose") {
                    defaultValue = null
                    nullable = true
                }
            ),
        ) {
            val purpose = it.savedStateHandle.get<String>("purpose")
                ?.let { Route.OtpPurpose.valueOf(it) }
                ?: return@composable

            val data = it.savedStateHandle.get<String>("data")

            OtpScreen(
                purpose = purpose,
                data = data,
                goToHome = {
                    controller.navigate(
                        Route.HomeDestinationBuilder(Route.HomeSection.COLORS)
                    )
                }
            )
        }

        composable(
            route = Route.HomeDestination,
            arguments = listOf(
                navArgument("section") {
                    defaultValue = Route.HomeSection.COLORS.name
                    nullable = true
                }
            ),
        ) {
            HomeScreen()
        }

        composable(
            route = Route.AboutDestination,
            deepLinks = listOf(
                navDeepLink { uriPattern = Route.AboutDeepLink },
                navDeepLink { uriPattern = Route.AboutCustomDeepLink }
            )
        ) {
            AboutScreen()
        }

        composable(
            route = Route.SecuritySettingDestination,
            deepLinks = listOf(
                navDeepLink { uriPattern = Route.SecuritySettingDeepLink },
                navDeepLink { uriPattern = Route.SecuritySettingCustomDeepLink }
            )
        ) {
            SecuritySettingScreen()
        }

        composable(
            route = Route.HelpAndSupportDestination,
            deepLinks = listOf(
                navDeepLink { uriPattern = Route.HelpAndSupportDeepLink },
                navDeepLink { uriPattern = Route.HelpAndSupportCustomDeepLink }
            )
        ) {
            HelpAndSupportScreen()
        }

        composable(
            route = Route.AccountSettingDestination,
            deepLinks = listOf(
                navDeepLink { uriPattern = Route.AccountSettingDeepLink },
                navDeepLink { uriPattern = Route.AccountSettingCustomDeepLink }
            )
        ) {
            AccountSettingScreen()
        }

        composable(
            route = Route.NotificationSettingDestination,
            deepLinks = listOf(
                navDeepLink { uriPattern = Route.NotificationSettingDeepLink },
                navDeepLink { uriPattern = Route.NotificationSettingCustomDeepLink }
            )
        ) {
            NotificationSettingScreen()
        }
    }
}
