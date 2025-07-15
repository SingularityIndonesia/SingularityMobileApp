package ui.pane

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import designsystem.component.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import ui.component.UserProfile
import ui.component.UserProfileDisplay
import ui.pane.AccountPaneEffect.FocusOnSearchInput
import ui.pane.AccountPaneIntent.*
import utils.CollectSideEffect
import utils.requestFocus
import kotlin.time.Duration.Companion.seconds

@Composable
fun AccountPane(
    modifier: Modifier = Modifier,
    viewModel: AccountPaneViewModel = koinViewModel(),
) {
    val state by viewModel.collectAsState()
    val searchInputFocusRequester = remember { FocusRequester() }

    CollectSideEffect(viewModel) {
        when (it) {
            FocusOnSearchInput -> searchInputFocusRequester.requestFocus(timeout = 2.seconds)
        }
    }

    AccountPane(
        modifier = modifier,
        state = state,
        searchInputFocusRequester = searchInputFocusRequester
    ) {
        when (it) {
            ShowSearch -> viewModel.showSearchBar()
            HideSearch -> viewModel.hideSearchBar()
            is SearchFor -> viewModel.searchFor(it.query)
        }
    }
}

@Composable
fun AccountPane(
    modifier: Modifier = Modifier,
    state: AccountPaneState = remember { AccountPaneState() },
    searchInputFocusRequester: FocusRequester = remember { FocusRequester() },
    onIntent: (AccountPaneIntent) -> Unit = {}
) {
    val uriHandler = LocalUriHandler.current
    val filteredMenu by rememberUpdatedState(state.filteredMenuItems)

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stickyHeader {
            Surface {
                TopAppBar(
                    onSearch = { onIntent(ShowSearch) }.takeIf { !state.showSearch },
                    onCloseSearch = { onIntent(HideSearch) }.takeIf { state.showSearch }
                )
            }
        }

        // User Profile Section (only show when not searching or search is disabled)
        if (!state.showSearch) {
            item {
                UserProfile(
                    userProfile = state.userProfile,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }

        // Search Bar (only show when search is enabled)
        if (state.showSearch) {
            stickyHeader {
                val resultCount by rememberUpdatedState(state.filteredMenuItems.size)
                Surface {
                    SearchSymbolic(
                        modifier = Modifier.padding(
                            horizontal = 16.dp
                        ).padding(bottom = 16.dp),
                        query = state.searchQuery,
                        buffered = state.enableSearchBuffering,
                        resultCount = resultCount,
                        focusRequester = searchInputFocusRequester,
                        onSearch = {
                            onIntent(SearchFor(it))
                        }
                    )
                }
            }
        }

        // Account Menu Items (filtered)
        if (filteredMenu.isNotEmpty()) {
            items(filteredMenu) { menuItem ->
                AccountMenuItem(
                    menuItem = menuItem,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    searchQuery = if (state.showSearch) state.searchQuery else "",
                    trailingActions = {
                        if (menuItem.title.contains("sign out", true))
                            SignOutSymbolic { }
                        else
                            NextSymbolic {
                                val deeplink = menuItem.actionDeepLink
                                check(deeplink != null) { return@NextSymbolic }
                                uriHandler.openUri(deeplink)
                            }
                    }
                )
            }
        } else if (state.showSearch && state.searchQuery.isNotEmpty()) {
            // No Results Found
            item {
                NoResultsFound(
                    searchQuery = state.searchQuery,
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
    Surface {
        AccountPane(
            state = AccountPaneState(
                userProfile = UserProfileDisplay(
                    name = "Steve the best",
                    email = "stefanus.ayudha@gmail.com",
                )
            )
        )
    }
}

@Preview
@Composable
private fun AccountPanePreviewOnSearch() {
    Surface {
        AccountPane(
            state = AccountPaneState(
                showSearch = true,
                searchQuery = "ac",
                enableSearchBuffering = false
            )
        )
    }
}

@Preview
@Composable
private fun AccountPanePreviewOnSearchNoResult() {
    Surface {
        AccountPane(
            state = AccountPaneState(
                showSearch = true,
                searchQuery = "asdad",
                enableSearchBuffering = false
            )
        )
    }
}