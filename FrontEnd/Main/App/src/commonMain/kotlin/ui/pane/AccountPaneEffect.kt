package ui.pane

import androidx.compose.runtime.Composable
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.compose.collectSideEffect

sealed class AccountPaneEffect {
    data object FocusOnSearchInput : AccountPaneEffect()
}
