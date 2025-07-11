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

    // region login
    val LoginDestination get() = "login"
    // endregion

    // region otp
    val OtpVerificationDestination get() = "otp-verification"
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
    val AboutDeepLink get() = "${webHostUrl}about"
    val AboutCustomDeepLink get() = "${deepLinkHostUrl}about"

    // endregion

    // region Security
    val SecuritySettingDestination get() = "security/setting"
    val SecuritySettingDeepLink get() = "${webHostUrl}security/setting"
    val SecuritySettingCustomDeepLink get() = "${deepLinkHostUrl}security/setting"

    // endregion

    // region Help
    val HelpAndSupportDestination get() = "help"
    val HelpAndSupportDeepLink get() = "${webHostUrl}help"
    val HelpAndSupportCustomDeepLink get() = "${deepLinkHostUrl}help"

    // endregion

    // region Account
    val AccountSettingDestination get() = "account/setting"
    val AccountSettingDeepLink get() = "${webHostUrl}account/setting"
    val AccountSettingCustomDeepLink get() = "${deepLinkHostUrl}account/setting"

    // endregion

    // region Notification
    val NotificationSettingDestination get() = "notification/setting"
    val NotificationSettingDeepLink get() = "${webHostUrl}notification/setting"
    val NotificationSettingCustomDeepLink get() = "${deepLinkHostUrl}notification/setting"

    // endregion
}
