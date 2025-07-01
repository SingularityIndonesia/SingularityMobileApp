import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import designsystem.SingularityTheme
import navigation.MainNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun App() {
    val navController = rememberNavController()
    val isDarkTheme = isSystemInDarkTheme()

    SingularityTheme(
        darkTheme = isDarkTheme
    ) {
        MainNavigation(
            modifier = Modifier.fillMaxSize(),
            controller = navController
        )
    }
}