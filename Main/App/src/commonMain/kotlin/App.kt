import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import designsystem.SingularityTheme

@Composable
fun App() {
    val navController = rememberNavController()
    val isDarkTheme = isSystemInDarkTheme()

    SingularityTheme(
        darkTheme = isDarkTheme
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