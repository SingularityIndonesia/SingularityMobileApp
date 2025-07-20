package ui.pane

import designsystemold.component.AccountMenuItemDisplay
import font.resources.*
import ui.component.UserProfileDisplay
import ui.navigation.Route

data class AccountPaneState(
    val userProfile: UserProfileDisplay = UserProfileDisplay(
        name = "Singularity",
        email = "singularity@gmail.com",
        profileImageUrl = "https://raw.githubusercontent.com/SingularityIndonesia/SingularityIndonesia/refs/heads/main/Logo%20Of%20Singularity%20Indonesia%20%C2%A92023%20Stefanus%20Ayudha%20-%20Circle.png"
    ),
    val showSearch: Boolean = false,
    val searchQuery: String = "",
    val enableSearchBuffering: Boolean = true,
    val menus: List<AccountMenuItemDisplay> = listOf(
        AccountMenuItemDisplay(
            title = "Account Settings",
            subtitle = "Privacy, security, and more",
            iconRes = Res.drawable.ic_person_filled,
            actionDeepLink = Route.AccountSettingCustomDeepLink
        ),
        AccountMenuItemDisplay(
            title = "Storage",
            subtitle = "32GB of 126GB used",
            iconRes = Res.drawable.ic_disk_filled,
        ),
        AccountMenuItemDisplay(
            title = "Privacy & Security",
            subtitle = "Control your data and privacy",
            iconRes = Res.drawable.ic_security_privacy_filled,
            actionDeepLink = Route.SecuritySettingCustomDeepLink
        ),
        AccountMenuItemDisplay(
            title = "Notifications",
            subtitle = "Manage your notification preferences",
            iconRes = Res.drawable.ic_notification_filled,
            actionDeepLink = Route.NotificationSettingCustomDeepLink
        ),
        AccountMenuItemDisplay(
            title = "Data & Storage",
            subtitle = "Network usage, auto-download",
            iconRes = Res.drawable.ic_download_filled,
        ),
        AccountMenuItemDisplay(
            title = "Help & Support",
            subtitle = "Get help and contact support",
            iconRes = Res.drawable.ic_support_agent_filled,
            actionDeepLink = Route.HelpAndSupportCustomDeepLink
        ),
        AccountMenuItemDisplay(
            title = "About",
            subtitle = "App info and legal",
            iconRes = Res.drawable.ic_info_filled,
            actionDeepLink = Route.AboutCustomDeepLink
        ),
        AccountMenuItemDisplay(
            title = "Sign Out",
            subtitle = "Logout and clear application's data",
            iconRes = Res.drawable.ic_nothing
        )
    )
) {
    val filteredMenuItems = if (!showSearch || searchQuery.isEmpty()) {
        menus
    } else {
        menus.filter { menuItem ->
            menuItem.title.contains(searchQuery, ignoreCase = true) ||
                    menuItem.subtitle?.contains(searchQuery, ignoreCase = true) == true
        }
    }
}