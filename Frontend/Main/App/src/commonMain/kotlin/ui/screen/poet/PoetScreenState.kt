package ui.screen.poet

import androidx.compose.foundation.text.input.TextFieldState
import arrow.optics.optics
import service.vault.web.response.VaultDocument
import utils.toDateTime

@optics
data class PoetScreenState(
    val title: String = "",
    val dateCreated: String = "",
    val mediaUris: List<String> = emptyList(),
    val textFieldState: TextFieldState = TextFieldState(),
    val isLoading: Boolean = false,
) {
    companion object

    fun composeWith(document: VaultDocument): PoetScreenState {
        return copy(
            title = document.content.title,
            dateCreated = document.dateCreated.toDateTime(),
            mediaUris = document.content.mediaUris
        )
    }
}