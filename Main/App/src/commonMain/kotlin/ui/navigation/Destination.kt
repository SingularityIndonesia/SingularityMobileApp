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

// endregion

// region Help
const val HelpAndSupportDestination = "help"

// endregion

// region Account
const val AccountSettingDestination = "account/setting"

// endregion

// region Notification
const val NotificationSettingDestination = "notification/setting"

// endregion

