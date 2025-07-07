package ui.navigation

/**
 * Utility class for testing deeplinks in development
 */
object DeepLinkTester {
    
    // Test URLs for AboutScreen
    const val ABOUT_HTTPS_DEEPLINK = "https://yourdomain.com/about"
    const val ABOUT_CUSTOM_DEEPLINK = "singularityapp://about"
    
    /**
     * Generate ADB commands for testing deeplinks on Android
     */
    fun generateAdbCommands(): List<String> {
        return listOf(
            "adb shell am start -W -a android.intent.action.VIEW -d \"$ABOUT_HTTPS_DEEPLINK\" com.singularityuniverse.singularity.android",
            "adb shell am start -W -a android.intent.action.VIEW -d \"$ABOUT_CUSTOM_DEEPLINK\" com.singularityuniverse.singularity.android"
        )
    }
    
    /**
     * Generate test scenarios for QA testing
     */
    fun getTestScenarios(): List<DeepLinkTestScenario> {
        return listOf(
            DeepLinkTestScenario(
                name = "About Screen - HTTPS",
                url = ABOUT_HTTPS_DEEPLINK,
                expectedDestination = AboutDestination,
                description = "Should navigate to About screen using HTTPS deeplink"
            ),
            DeepLinkTestScenario(
                name = "About Screen - Custom Scheme",
                url = ABOUT_CUSTOM_DEEPLINK,
                expectedDestination = AboutDestination,
                description = "Should navigate to About screen using custom scheme deeplink"
            )
        )
    }
}

data class DeepLinkTestScenario(
    val name: String,
    val url: String,
    val expectedDestination: String,
    val description: String
)
