package ui.pane

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import main.app.generated.resources.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.designsystem.component.*

private val menus = listOf(
    AccountMenuItemDisplay(
        title = "Account Settings",
        subtitle = "Privacy, security, and more",
        iconRes = Res.drawable.ic_person,
    ),
    AccountMenuItemDisplay(
        title = "Storage",
        subtitle = "32GB of 126GB used",
        iconRes = Res.drawable.ic_storage
    ),
    AccountMenuItemDisplay(
        title = "Privacy & Security",
        subtitle = "Control your data and privacy",
        iconRes = Res.drawable.ic_security_privacy,
    ),
    AccountMenuItemDisplay(
        title = "Notifications",
        subtitle = "Manage your notification preferences",
        iconRes = Res.drawable.ic_notification
    ),
    AccountMenuItemDisplay(
        title = "Data & Storage",
        subtitle = "Network usage, auto-download",
        iconRes = Res.drawable.ic_download,
    ),
    AccountMenuItemDisplay(
        title = "Help & Support",
        subtitle = "Get help and contact support",
        iconRes = Res.drawable.ic_support_agent,
    ),
    AccountMenuItemDisplay(
        title = "About",
        subtitle = "App info and legal",
        iconRes = Res.drawable.ic_info
    ),
    AccountMenuItemDisplay(
        title = "Sign Out",
        subtitle = "Exit account",
        iconRes = Res.drawable.ic_nothing
    )
)

@Composable
fun AccountPane(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    userProfile: UserProfileDisplay = UserProfileDisplay(
        name = "Uwuu",
        initialName = "U",
        profileImageUrl = "https://cnc-magazine.oramiland.com/parenting/images/kim-da-hyun.width-800.format-webp.webp",
        email = "uwuu@example.com"
    ),
    showSearch: Boolean = false,
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
) {

    val filteredMenuItems = remember(searchQuery, menus, showSearch) {
        if (!showSearch || searchQuery.isEmpty()) {
            menus
        } else {
            menus.filter { menuItem ->
                menuItem.title.contains(searchQuery, ignoreCase = true) ||
                        menuItem.subtitle?.contains(searchQuery, ignoreCase = true) == true
            }
        }
    }

    val searchInputFocusRequester = remember { FocusRequester() }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = remember(contentPadding) {
            PaddingValues(
                top = contentPadding.calculateTopPadding(),
                bottom = contentPadding.calculateBottomPadding() + 16.dp,
                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr),
                end = contentPadding.calculateStartPadding(LayoutDirection.Rtl),
            )
        },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // User Profile Section (only show when not searching or search is disabled)
        if (!showSearch) {
            item {
                UserProfile(
                    userProfile = userProfile,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                )
            }
        }

        // Search Bar (only show when search is enabled)
        if (showSearch) {
            stickyHeader {
                if (searchQuery.isNotEmpty()) {
                    SearchResultsHeader(
                        resultsCount = filteredMenuItems.size,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )

                    Spacer(Modifier.height(8.dp))
                }

                SearchBar(
                    query = searchQuery,
                    onQueryChange = onSearchQueryChange,
                    placeholder = "Search settings...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .focusRequester(searchInputFocusRequester)
                )

                Spacer(Modifier.height(8.dp))

                LaunchedEffect(Unit) {
                    searchInputFocusRequester.requestFocus()
                }
            }
        }

        // Account Menu Items (filtered)
        if (filteredMenuItems.isNotEmpty()) {
            items(filteredMenuItems) { menuItem ->
                AccountMenuItemCard(
                    menuItem = menuItem,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    searchQuery = if (showSearch) searchQuery else "",
                    trailingActions = {
                        if (menuItem.title.contains("sign out", true))
                            SignOut { }
                        else
                            Next { }
                    }
                )
            }
        } else if (showSearch && searchQuery.isNotEmpty()) {
            // No Results Found
            item {
                NoResultsFound(
                    searchQuery = searchQuery,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 32.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun AccountPanePreview() {
    MaterialTheme {
        AccountPane(
            contentPadding = PaddingValues(16.dp),
            userProfile = UserProfileDisplay(
                name = "Jane Smith",
                email = "jane.smith@example.com",
                initialName = "JS",
            ),
            onSearchQueryChange = {}
        )
    }
}

@Preview
@Composable
private fun AccountPanePreviewOnSearch() {
    MaterialTheme {
        AccountPane(
            contentPadding = PaddingValues(16.dp),
            userProfile = UserProfileDisplay(
                name = "Jane Smith",
                email = "jane.smith@example.com",
                initialName = "JS",
            ),
            showSearch = true,
            searchQuery = "settings",
            onSearchQueryChange = {}
        )
    }
}