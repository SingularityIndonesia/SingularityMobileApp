package ui.navigation

// region Home
const val HomeDestination = "home?section={section}"

enum class HomeSection {
    Colors,
    Memories,
    Search,
    Account
}

fun HomeDestinationBuilder(
    section: HomeSection = HomeSection.Colors
): String {
    return HomeDestination
        .replace("{section}", section.name)
}
// endregion

// region About
const val AboutDestination = "about"
const val AboutDeepLink = "https://yourdomain.com/about"
const val AboutCustomDeepLink = "singularityapp://about"

// endregion

// region Security
const val SecuritySettingDestination = "security/setting"
const val SecuritySettingDeepLink = "https://yourdomain.com/security/setting"
const val SecuritySettingCustomDeepLink = "singularityapp://security/setting"

// endregion

// region Help
const val HelpAndSupportDestination = "help"
const val HelpAndSupportDeepLink = "https://yourdomain.com/help"
const val HelpAndSupportCustomDeepLink = "singularityapp://help"

// endregion

// region Account
const val AccountSettingDestination = "account/setting"
const val AccountSettingDeepLink = "https://yourdomain.com/account/setting"
const val AccountSettingCustomDeepLink = "singularityapp://account/setting"

// endregion

// region Notification
const val NotificationSettingDestination = "notification/setting"
const val NotificationSettingDeepLink = "https://yourdomain.com/notification/setting"
const val NotificationSettingCustomDeepLink = "singularityapp://notification/setting"

// endregion

