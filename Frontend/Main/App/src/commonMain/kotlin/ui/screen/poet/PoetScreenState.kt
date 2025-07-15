package ui.screen.poet

import androidx.compose.foundation.text.input.TextFieldState
import service.vault.web.response.VaultDocumentResponse

data class PoetScreenState(
    val document: VaultDocumentResponse = VaultDocumentResponse(),
    val textFieldState: TextFieldState = TextFieldState(),
    val isLoading: Boolean = false,
)