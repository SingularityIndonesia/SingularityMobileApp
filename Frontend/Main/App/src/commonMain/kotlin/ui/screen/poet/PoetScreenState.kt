package ui.screen.poet

import androidx.compose.foundation.text.input.TextFieldState
import model.VaultDocument

data class PoetScreenState(
    val document: VaultDocument = VaultDocument(),
    val textFieldState: TextFieldState = TextFieldState(),
    val isLoading: Boolean = false,
)