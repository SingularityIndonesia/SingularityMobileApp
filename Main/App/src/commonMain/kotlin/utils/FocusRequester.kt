package utils

import androidx.compose.ui.focus.FocusRequester
import kotlin.time.Duration

suspend fun FocusRequester.requestFocus(timeout: Duration): Boolean {
    temporaryLoop(timeout = timeout) {
        requestFocus()
    }
    return false
}