package ui.screen.poet

import androidx.compose.foundation.text.input.TextFieldState
import utils.dateTime

data class PoetScreenState(
    val documentId: String? = null,
    val title: String = "Tanpa Judul",
    val creationDate: String = dateTime(),
    val textFieldState: TextFieldState = TextFieldState(),
    val mediaUris: List<String> = emptyList(),
    val isLoading: Boolean = false,
)