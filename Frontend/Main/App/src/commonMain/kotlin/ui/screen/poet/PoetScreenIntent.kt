package ui.screen.poet

sealed class PoetScreenIntent {
    data class AddMedia(val uris: List<String>) : PoetScreenIntent()
    data class RemoveMedia(val uri: String) : PoetScreenIntent()
    data class SaveDocument(val title: String) : PoetScreenIntent()
    data class UpdateTitle(val title: String) : PoetScreenIntent()
    data object NavigateBack : PoetScreenIntent()
}