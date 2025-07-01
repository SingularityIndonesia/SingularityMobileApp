import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pane.HomePane

@Composable
fun MainNavigation(
    controller: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = controller,
        startDestination = "home"
    ) {
        composable(route = "home") {
            HomePane()
        }
    }
}