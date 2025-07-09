import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.designsystem.SingularityTheme
import ui.navigation.MainNavigation
import ui.navigation.rememberDeepLinkHandler

@Preview
@Composable
fun App(
    intent: AppIntent? = null,
    onHandled: (AppIntent) -> Unit = {}
) {
    val navController = rememberNavController()
    val isDarkTheme = isSystemInDarkTheme()
    val deepLinkHandler = rememberDeepLinkHandler(navController)

    IntentHandlerEffect(
        intent,
        deepLinkHandler,
        onHandled
    )

    SingularityTheme(
        darkTheme = isDarkTheme
    ) {
        MainNavigation(
            modifier = Modifier.fillMaxSize(),
            controller = navController
        )
    }
}