package ui.pane

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import font.resources.*
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.compose.collectSideEffect
import org.orbitmvi.orbit.viewmodel.container
import ui.designsystem.component.AccountMenuItemDisplay
import ui.designsystem.component.UserProfileDisplay
import ui.navigation.*
import ui.navigation.Route.AboutCustomDeepLink
import ui.navigation.Route.AccountSettingCustomDeepLink
import ui.navigation.Route.HelpAndSupportCustomDeepLink
import ui.navigation.Route.NotificationSettingCustomDeepLink
import ui.navigation.Route.SecuritySettingCustomDeepLink

data class AccountPaneState(
    val userProfile: UserProfileDisplay = UserProfileDisplay(
        name = "Singularity",
        email = "singularity@gmail.com",
        profileImageUrl = "https://github.com/SingularityIndonesia/SingularityIndonesia/blob/main/Logo%20Of%20Singularity%20Indonesia%20%C2%A92023%20Stefanus%20Ayudha%20-%20Circle.png"
    ),
    val showSearch: Boolean = false,
    val searchQuery: String = "",
    val enableSearchBuffering: Boolean = true,
    val menus: List<AccountMenuItemDisplay> = listOf(
        AccountMenuItemDisplay(
            title = "Account Settings",
            subtitle = "Privacy, security, and more",
            iconRes = Res.drawable.ic_person_filled,
            actionDeepLink = AccountSettingCustomDeepLink
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
            actionDeepLink = SecuritySettingCustomDeepLink
        ),
        AccountMenuItemDisplay(
            title = "Notifications",
            subtitle = "Manage your notification preferences",
            iconRes = Res.drawable.ic_notification_filled,
            actionDeepLink = NotificationSettingCustomDeepLink
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
            actionDeepLink = HelpAndSupportCustomDeepLink
        ),
        AccountMenuItemDisplay(
            title = "About",
            subtitle = "App info and legal",
            iconRes = Res.drawable.ic_info_filled,
            actionDeepLink = AboutCustomDeepLink
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

sealed class AccountPaneEffect {
    data object FocusOnSearchInput : AccountPaneEffect()
}

sealed class AccountPaneIntent {
    data object ShowSearch : AccountPaneIntent()
    data object HideSearch : AccountPaneIntent()
    data class SearchFor(val query: String): AccountPaneIntent()
}

@Composable
fun CollectSideEffect(
    containerHost: ContainerHost<AccountPaneState, AccountPaneEffect>,
    onEffect: suspend (AccountPaneEffect) -> Unit
) {
    containerHost.collectSideEffect { onEffect.invoke(it) }
}

class AccountPaneViewModel : ContainerHost<AccountPaneState, AccountPaneEffect>, ViewModel() {
    override val container: Container<AccountPaneState, AccountPaneEffect> = container(AccountPaneState())

    fun showSearchBar() = intent {
        reduce {
            state.copy(
                showSearch = true
            )
        }

        postSideEffect(AccountPaneEffect.FocusOnSearchInput)
    }

    fun hideSearchBar() = intent {
        reduce {
            state.copy(
                showSearch = false,
                searchQuery = ""
            )
        }
    }

    fun searchFor(query: String) = intent {
        reduce {
            state.copy(
                searchQuery = query
            )
        }
    }
}