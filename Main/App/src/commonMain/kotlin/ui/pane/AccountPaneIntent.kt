package ui.pane

sealed class AccountPaneIntent {
    data object ShowSearch : AccountPaneIntent()
    data object HideSearch : AccountPaneIntent()
    data class SearchFor(val query: String): AccountPaneIntent()
}