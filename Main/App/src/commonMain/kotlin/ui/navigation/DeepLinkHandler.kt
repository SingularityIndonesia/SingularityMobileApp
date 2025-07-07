package ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

/**
 * Utility class to handle deeplinks throughout the app
 */
class DeepLinkHandler(private val navController: NavHostController) {
    /**
     * Navigate to any destination with deeplink support
     */
    fun navigateToDestination(destination: String) {
        navController.navigate(destination) {
            launchSingleTop = true
            restoreState = true
        }
    }

    /**
     * Handle deeplink URLs
     */
    fun handleDeepLink(url: String): Boolean {
        val destination = when {
            url.contains("/about") -> AboutDestination
            url.contains("/security/setting") -> SecuritySettingDestination
            url.contains("/help") -> HelpAndSupportDestination
            url.contains("/account/setting") -> AccountSettingDestination
            url.contains("/notification/setting") -> NotificationSettingDestination
            else -> null
        }
        check(destination != null) { return false }
        navigateToDestination(destination)
        return true
    }
}

/**
 * Extension function to create DeepLinkHandler
 */
@Composable
fun rememberDeepLinkHandler(controller: NavHostController): DeepLinkHandler {
    return remember { DeepLinkHandler(controller) }
}
