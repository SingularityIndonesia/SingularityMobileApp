package ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

/**
 * Utility class to handle deeplinks throughout the app
 */
class DeepLinkHandler(private val navController: NavHostController) {

    /**
     * Navigate to About screen
     */
    fun navigateToAbout() {
        navController.navigate(AboutDestination) {
            launchSingleTop = true
            restoreState = true
        }
    }

    /**
     * Navigate to Security Setting screen
     */
    fun navigateToSecuritySetting() {
        navController.navigate(SecuritySettingDestination) {
            launchSingleTop = true
            restoreState = true
        }
    }

    /**
     * Navigate to Help and Support screen
     */
    fun navigateToHelpAndSupport() {
        navController.navigate(HelpAndSupportDestination) {
            launchSingleTop = true
            restoreState = true
        }
    }

    /**
     * Navigate to Account Setting screen
     */
    fun navigateToAccountSetting() {
        navController.navigate(AccountSettingDestination) {
            launchSingleTop = true
            restoreState = true
        }
    }

    /**
     * Navigate to Notification Setting screen
     */
    fun navigateToNotificationSetting() {
        navController.navigate(NotificationSettingDestination) {
            launchSingleTop = true
            restoreState = true
        }
    }

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
        return when {
            url.contains("/about") -> {
                navigateToAbout()
                true
            }

            url.contains("/security/setting") -> {
                navigateToSecuritySetting()
                true
            }

            url.contains("/help") -> {
                navigateToHelpAndSupport()
                true
            }

            url.contains("/account/setting") -> {
                navigateToAccountSetting()
                true
            }

            url.contains("/notification/setting") -> {
                navigateToNotificationSetting()
                true
            }

            else -> false
        }
    }
}

/**
 * Extension function to create DeepLinkHandler
 */
@Composable
fun rememberDeepLinkHandler(controller: NavHostController): DeepLinkHandler {
    return remember { DeepLinkHandler(controller) }
}
