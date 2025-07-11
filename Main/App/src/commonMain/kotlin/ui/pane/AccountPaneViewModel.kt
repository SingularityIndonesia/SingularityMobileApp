package ui.pane

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.compose.collectSideEffect
import org.orbitmvi.orbit.viewmodel.container

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