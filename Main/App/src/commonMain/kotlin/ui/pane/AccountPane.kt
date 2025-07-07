package ui.pane

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import main.app.generated.resources.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.designsystem.component.*


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

    val accountMenuItems = remember {
        listOf(
            AccountMenuItemCardDisplay(
                title = "Account Settings",
                subtitle = "Privacy, security, and more",
                iconRes = Res.drawable.ic_person,
            ),
            AccountMenuItemCardDisplay(
                title = "Storage",
                subtitle = "32GB of 126GB used",
                iconRes = Res.drawable.ic_gallery
            ),
            AccountMenuItemCardDisplay(
                title = "Privacy & Security",
                subtitle = "Control your data and privacy",
                iconRes = Res.drawable.ic_palette,
            ),
            AccountMenuItemCardDisplay(
                title = "Notifications",
                subtitle = "Manage your notification preferences",
                iconRes = Res.drawable.ic_search
            ),
            AccountMenuItemCardDisplay(
                title = "Data & Storage",
                subtitle = "Network usage, auto-download",
                iconRes = Res.drawable.ic_gallery,
                showDivider = true
            ),
            AccountMenuItemCardDisplay(
                title = "Help & Support",
                subtitle = "Get help and contact support",
                iconRes = Res.drawable.ic_search,
            ),
            AccountMenuItemCardDisplay(
                title = "About",
                subtitle = "App info and legal",
                iconRes = Res.drawable.compose_multiplatform
            ),
            AccountMenuItemCardDisplay(
                title = "Sign Out",
                subtitle = null,
                iconRes = Res.drawable.ic_more_horz,
            )
        )
    }

    val filteredMenuItems = remember(searchQuery, accountMenuItems, showSearch) {
        if (!showSearch || searchQuery.isEmpty()) {
            accountMenuItems
        } else {
            accountMenuItems.filter { menuItem ->
                menuItem.title.contains(searchQuery, ignoreCase = true) ||
                        menuItem.subtitle?.contains(searchQuery, ignoreCase = true) == true
            }
        }
    }

    val searchInputFocusRequester = remember { FocusRequester() }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // User Profile Section (only show when not searching or search is disabled)
        if (!showSearch) {
            item {
                UserProfileCard(
                    userProfile = userProfile,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
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
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    searchQuery = if (showSearch) searchQuery else ""
                )

                if (menuItem.showDivider && (!showSearch || searchQuery.isEmpty())) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 32.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                    )
                }
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

        item {
            Spacer(modifier = Modifier.height(32.dp))
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