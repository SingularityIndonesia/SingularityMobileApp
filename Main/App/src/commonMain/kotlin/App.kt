import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import designsystem.DarkThemePalette
import designsystem.LightThemePalette

@Composable
fun App() {
    val navController = rememberNavController()
    val colorScheme = if (isSystemInDarkTheme()) DarkThemePalette else LightThemePalette

    MaterialTheme(
        colorScheme = colorScheme
    ) {
        Scaffold(
            topBar = {

            },
            bottomBar = {

            }
        ) {
            MainNavigation(
                modifier = Modifier.padding(it),
                controller = navController
            )
        }
    }
}