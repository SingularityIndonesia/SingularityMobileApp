import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import designsystem.SingularityTheme
import navigation.navigator.BottomNavigation
import navigation.MainNavigation
import navigation.navigator.TopAppBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun App() {
    val navController = rememberNavController()
    val isDarkTheme = isSystemInDarkTheme()

    SingularityTheme(
        darkTheme = isDarkTheme
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    navController = navController
                )
            },
            bottomBar = {
                BottomNavigation(
                    modifier = Modifier.fillMaxWidth(),
                    navController = navController
                )
            }
        ) {
            MainNavigation(
                modifier = Modifier
                    .padding(it),
                controller = navController
            )
        }
    }
}