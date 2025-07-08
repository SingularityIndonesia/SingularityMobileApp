package ui.pane

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ui.designsystem.component.*
import ui.screen.home.CommonTopAppBar
import utils.requestFocus
import kotlin.time.Duration.Companion.seconds

@Composable
fun AccountPane(
    modifier: Modifier = Modifier,
    viewModel: AccountPaneViewModel = viewModel { AccountPaneViewModel() },
) {
    val state by viewModel.collectAsState()
    val uriHandler = LocalUriHandler.current
    val searchInputFocusRequester = remember { FocusRequester() }
    val filteredMenu by rememberUpdatedState(state.filteredMenuItems)

    CollectSideEffect(viewModel) {
        when (it) {
            AccountPaneEffect.FocusOnSearchInput -> searchInputFocusRequester.requestFocus(timeout = 2.seconds)
        }
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stickyHeader {
            TopAppBar(viewModel)
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
                SearchSection(
                    viewModel = viewModel,
                    focusRequester = searchInputFocusRequester
                )
            }
        }

        // Account Menu Items (filtered)
        if (filteredMenu.isNotEmpty()) {
            items(filteredMenu) { menuItem ->
                Spacer(Modifier.height(16.dp))
                AccountMenuItem(
                    menuItem = menuItem,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    searchQuery = if (state.showSearch) state.searchQuery else "",
                    trailingActions = {
                        if (menuItem.title.contains("sign out", true))
                            SignOut { }
                        else
                            Next {
                                check(menuItem.actionDeepLink != null) { return@Next }
                                uriHandler.openUri(menuItem.actionDeepLink)
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
private fun TopAppBar(
    viewModel: AccountPaneViewModel = viewModel { AccountPaneViewModel() }
) {
    val state by viewModel.collectAsState()

    CommonTopAppBar(
        titleText = "Account"
    ) {
        when {
            !state.showSearch -> {
                Search { viewModel.showSearchBar() }
            }

            state.showSearch -> {
                CloseSearch { viewModel.hideSearchBar() }
            }

            else -> {}
        }
    }
}

@Composable
fun SearchSection(
    viewModel: AccountPaneViewModel = viewModel { AccountPaneViewModel() },
    focusRequester: FocusRequester = remember { FocusRequester() }
) {
    val state by viewModel.collectAsState()
    val filteredMenu by rememberUpdatedState(state.filteredMenuItems)

    Surface {
        Column {
            if (state.searchQuery.isNotEmpty()) {
                SearchResultsHeader(
                    resultsCount = filteredMenu.size,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(8.dp))
            }

            SearchBar(
                query = state.searchQuery,
                onQueryChange = {
                    viewModel.searchFor(it)
                },
                placeholder = "Search settings...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .focusRequester(focusRequester)
            )

            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
private fun AccountPanePreview() {
    MaterialTheme {
        Surface {
            AccountPane()
        }
    }
}

@Composable
fun CollectSideEffect(
    containerHost: ContainerHost<AccountPaneState, AccountPaneEffect>,
    onEffect: suspend (AccountPaneEffect) -> Unit
) {
    containerHost.collectSideEffect { onEffect.invoke(it) }
}

@Preview
@Composable
private fun AccountPanePreviewOnSearch() {
    MaterialTheme {
        Surface {
            AccountPane()
        }
    }
}