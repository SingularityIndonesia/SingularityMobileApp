import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ui.navigation.DeepLinkHandler

sealed class AppIntent {
    open val requireLoggedIn: Boolean = false

    class DeepLinkNavigate(val deepLinkUri: String) : AppIntent()
}

@Composable
fun IntentHandlerEffect(intent: AppIntent?, deepLinkHandler: DeepLinkHandler, onHandled: (AppIntent) -> Unit) {
    // Handle Intent
    LaunchedEffect(intent, deepLinkHandler) {
        when (intent) {
            is AppIntent.DeepLinkNavigate -> {
                deepLinkHandler.handleDeepLink(intent.deepLinkUri)
            }

            null -> {}
        }

        check(intent != null) { return@LaunchedEffect }
        onHandled.invoke(intent)
    }
}