package navigation.navigator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import main.app.generated.resources.Res
import main.app.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import utils.currentDestination

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val currentDestination by currentDestination(navController)
    val title by rememberUpdatedState(
        currentDestination?.route
            ?.split("?")
            ?.first()
            ?.replaceFirstChar { it.titlecase() }
            ?: ""
    )
    val canGoBack by rememberUpdatedState(
        title.lowercase() !in listOf("home")
    )

    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            if (canGoBack)
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null
                    )
                }
        },
        title = {
            Text(title)
        }
    )
}