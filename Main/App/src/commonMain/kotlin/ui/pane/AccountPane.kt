package ui.pane

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.util.date.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ui.designsystem.component.*
import ui.screen.home.CommonTopAppBar
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

suspend fun FocusRequester.requestFocus(timeout: Duration): Boolean {
    val timeoutMillis = timeout.inWholeMilliseconds // 2 seconds max
    val tic = getTimeMillis()
    val toc = { getTimeMillis() - tic }

    while (coroutineContext.isActive && (toc() < timeoutMillis)) {
        runCatching { requestFocus() }
            .onSuccess { return true }
        delay(100)
    }

    return false
}

@Composable
fun AccountPane(
    modifier: Modifier = Modifier,
    viewModel: AccountPaneViewModel = viewModel { AccountPaneViewModel() },
) {
    val state by viewModel.collectAsState()
    val scope = rememberCoroutineScope()
    val uriHandler = LocalUriHandler.current
    val searchInputFocusRequester = remember { FocusRequester() }
    val filteredMenu by rememberUpdatedState(state.filteredMenuItems)

    CollectSideEffect(viewModel) {
        when (it) {
            AccountPaneEffect.FocusOnSearchInput -> scope.launch {
                val result = searchInputFocusRequester.requestFocus(timeout = 2.seconds)
                if (result) {
                    println("Success searchInputFocusRequester")
                }else {
                    println("Error searchInputFocusRequester")
                }
            }
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
    onEffect: (AccountPaneEffect) -> Unit
) {
    containerHost.collectSideEffect {
        onEffect.invoke(it)
    }
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