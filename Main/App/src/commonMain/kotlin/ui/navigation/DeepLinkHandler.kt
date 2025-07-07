package ui.navigation

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
            // Add more deeplink patterns here
            else -> false
        }
    }
}

/**
 * Extension function to create DeepLinkHandler
 */
fun NavHostController.createDeepLinkHandler(): DeepLinkHandler {
    return DeepLinkHandler(this)
}
