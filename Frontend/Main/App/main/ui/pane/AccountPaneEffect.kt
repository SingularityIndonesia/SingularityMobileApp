package ui.pane

sealed class AccountPaneEffect {
    data object FocusOnSearchInput : AccountPaneEffect()
}
