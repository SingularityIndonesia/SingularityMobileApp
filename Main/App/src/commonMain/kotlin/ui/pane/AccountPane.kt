package ui.pane

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.orbitmvi.orbit.compose.collectAsState
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
    val searchInputFocusRequester = remember { FocusRequester() }

    CollectSideEffect(viewModel) {
        when (it) {
            AccountPaneEffect.FocusOnSearchInput -> searchInputFocusRequester.requestFocus(timeout = 2.seconds)
        }
    }

    AccountPane(
        modifier = modifier,
        state = state,
        searchInputFocusRequester = searchInputFocusRequester
    ) {
        when (it) {
            AccountPaneIntent.ShowSearch -> viewModel.showSearchBar()
            AccountPaneIntent.HideSearch -> viewModel.hideSearchBar()
            is AccountPaneIntent.Search -> viewModel.searchFor(it.query)
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
            TopAppBar(
                state = state,
                onShowSearch = {
                    onIntent(AccountPaneIntent.ShowSearch)
                },
                onHideSearch = {
                    onIntent(AccountPaneIntent.HideSearch)
                }
            )
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
                Surface {
                    SearchSection(
                        state = state,
                        focusRequester = searchInputFocusRequester,
                        onSearch = {
                            onIntent(AccountPaneIntent.Search(it))
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
private fun AccountPanePreview() {
    Surface {
        AccountPane(
            state = AccountPaneState()
        )
    }
}

@Preview
@Composable
private fun AccountPanePreviewOnSearch() {
    Surface {
        AccountPane(
            state = AccountPaneState(
                showSearch = true
            )
        )
    }
}

@Composable
private fun TopAppBar(
    state: AccountPaneState = AccountPaneState(),
    onShowSearch: () -> Unit = {},
    onHideSearch: () -> Unit = {}
) {
    CommonTopAppBar(
        titleText = "Account"
    ) {
        when {
            !state.showSearch -> {
                Search { onShowSearch() }
            }

            state.showSearch -> {
                CompositionLocalProvider(LocalIconButtonColor provides IconButtonDefaults.filledTonalIconButtonColors()) {
                    CloseSearch { onHideSearch() }
                }
            }

            else -> {}
        }
    }
}

@Preview
@Composable
private fun TopAppBarPreview() {
    Surface {
        TopAppBar(
            state = AccountPaneState(),
        )
    }
}

@Composable
fun SearchSection(
    state: AccountPaneState = remember { AccountPaneState(searchQuery = "alskd") },
    buffered: Boolean = false,
    focusRequester: FocusRequester = remember { FocusRequester() },
    onSearch: (String) -> Unit = {},
) {
    val filteredMenu by rememberUpdatedState(state.filteredMenuItems)
    Column {
        val buffer = remember { mutableStateOf("") }
        SearchBar(
            query = if (buffered) buffer.value else state.searchQuery,
            onQueryChange = {
                if (buffered)
                    buffer.value = it

                onSearch(it)
            },
            placeholder = "Search settings...",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .focusRequester(focusRequester)
        )
        Spacer(Modifier.height(8.dp))

        if (state.searchQuery.isNotEmpty()) {
            SearchResultsHelper(
                resultsCount = filteredMenu.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
private fun SearchSectionPreview() {
    Surface {
        SearchSection()
    }
}