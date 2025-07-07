import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.designsystem.SingularityTheme
import ui.navigation.MainNavigation
import ui.navigation.rememberDeepLinkHandler

@Preview
@Composable
fun App(
    deepLinkUrl: String? = null
) {
    val navController = rememberNavController()
    val isDarkTheme = isSystemInDarkTheme()
    val deepLinkHandler = rememberDeepLinkHandler(navController)

    // Handle deeplink when app starts
    LaunchedEffect(deepLinkUrl) {
        deepLinkUrl?.let { url ->
            deepLinkHandler.handleDeepLink(url)
        }
    }

    SingularityTheme(
        darkTheme = isDarkTheme
    ) {
        MainNavigation(
            modifier = Modifier.fillMaxSize(),
            controller = navController
        )
    }
}
