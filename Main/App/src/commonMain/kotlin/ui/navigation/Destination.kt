package ui.navigation

import ProjectContext

object Route {
    // region Init
    var projectContext: ProjectContext = ProjectContext()
        private set
    val webHostUrl = projectContext.webHostUrl
    val deepLinkHostUrl = projectContext.deepLinkHostUrl

    fun set(projectContext: ProjectContext) {
        this.projectContext = projectContext
    }
    // endregion

    // region Home
    val HomeDestination get() = "home?section={section}"

    enum class HomeSection {
        Colors,
        Memories,
        Search,
        Account
    }

    fun HomeDestinationBuilder(
        section: HomeSection = HomeSection.Colors
    ): String {
        return HomeDestination.replace("{section}", section.name)
    }
    // endregion

    // region About
    val AboutDestination get() = "about"
    val AboutDeepLink = "${webHostUrl}about"
    val AboutCustomDeepLink = "${deepLinkHostUrl}about"

    // endregion

    // region Security
    val SecuritySettingDestination get() = "security/setting"
    val SecuritySettingDeepLink = "${webHostUrl}security/setting"
    val SecuritySettingCustomDeepLink = "${deepLinkHostUrl}security/setting"

    // endregion

    // region Help
    val HelpAndSupportDestination get() = "help"
    val HelpAndSupportDeepLink = "${webHostUrl}help"
    val HelpAndSupportCustomDeepLink = "${deepLinkHostUrl}help"

    // endregion

    // region Account
    val AccountSettingDestination get() = "account/setting"
    val AccountSettingDeepLink = "${webHostUrl}account/setting"
    val AccountSettingCustomDeepLink = "${deepLinkHostUrl}account/setting"

    // endregion

    // region Notification
    val NotificationSettingDestination get() = "notification/setting"
    val NotificationSettingDeepLink = "${webHostUrl}notification/setting"
    val NotificationSettingCustomDeepLink = "${deepLinkHostUrl}notification/setting"

    // endregion
}
