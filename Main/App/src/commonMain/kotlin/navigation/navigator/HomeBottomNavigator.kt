package navigation.navigator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import main.app.generated.resources.Res
import main.app.generated.resources.compose_multiplatform
import navigation.HomeDestinationBuilder
import navigation.HomeSection
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import utils.currentDestinationArgument

@Preview
@Composable
fun HomeBottomBar(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    val argument by currentDestinationArgument(navController)
    val section by rememberUpdatedState(
        argument?.getString("section")
            ?.let { HomeSection.valueOf(it) }
            ?: HomeSection.Universe
    )

    BottomAppBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            selected = section == HomeSection.Universe,
            onClick = {
                navController.navigate(
                    route = HomeDestinationBuilder(
                        section = HomeSection.Universe
                    )
                ) {
                    launchSingleTop = true
                }
            },
            label = {
                Text("Universe")
            },
            icon = {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = null
                )
            }
        )

        NavigationBarItem(
            selected = section == HomeSection.Page1,
            onClick = {
                navController.navigate(
                    route = HomeDestinationBuilder(
                        section = HomeSection.Page1
                    )
                ) {
                    launchSingleTop = true
                }
            },
            label = {
                Text("Universe")
            },
            icon = {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = null
                )
            }
        )

        NavigationBarItem(
            selected = section == HomeSection.Page2,
            onClick = {
                navController.navigate(
                    route = HomeDestinationBuilder(
                        section = HomeSection.Page2
                    )
                ) {
                    launchSingleTop = true
                }
            },
            label = {
                Text("Universe")
            },
            icon = {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = null
                )
            }
        )

        NavigationBarItem(
            selected = section == HomeSection.Page3,
            onClick = {
                navController.navigate(
                    route = HomeDestinationBuilder(
                        section = HomeSection.Page3
                    )
                ) {
                    launchSingleTop = true
                }
            },
            label = {
                Text("Universe")
            },
            icon = {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = null
                )
            }
        )
    }
}