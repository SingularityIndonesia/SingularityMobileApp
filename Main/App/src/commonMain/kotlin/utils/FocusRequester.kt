package utils

import androidx.compose.ui.focus.FocusRequester
import io.ktor.util.date.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration

suspend fun FocusRequester.requestFocus(timeout: Duration): Boolean {
    val timeoutMillis = timeout.inWholeMilliseconds // 2 seconds max
    val tic = getTimeMillis()
    val toc = { getTimeMillis() - tic }
    var error = ""

    while (coroutineContext.isActive && (toc() < timeoutMillis)) {
        runCatching { requestFocus() }
            .onFailure { error = "Error: requestFocus ${it.message ?: it.cause?.message ?: it}" }
            .onSuccess {
                println("Success: requestFocus")
                return true
            }
        delay(100)
    }

    println("Error: requestFocus TIMEOUT\n$error")
    return false
}