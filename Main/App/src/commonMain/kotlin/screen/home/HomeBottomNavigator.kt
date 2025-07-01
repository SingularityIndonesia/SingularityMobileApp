package screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import main.app.generated.resources.Res
import main.app.generated.resources.compose_multiplatform
import navigation.HomeSection
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun HomeBottomBar(
    pagerState: PagerState = rememberPagerState(0) { 4 },
    onItemClicked: (HomeSection) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val currentSection by rememberUpdatedState(
        runCatching { HomeSection.entries[pagerState.currentPage] }
            .getOrElse { HomeSection.Universe }
    )

    BottomAppBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            selected = currentSection == HomeSection.Universe,
            onClick = {
                onItemClicked.invoke(HomeSection.Universe)
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
            selected = currentSection == HomeSection.Page1,
            onClick = {
                onItemClicked.invoke(HomeSection.Page1)
            },
            label = {
                Text("Page1")
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
            selected = currentSection == HomeSection.Page2,
            onClick = {
                onItemClicked.invoke(HomeSection.Page2)
            },
            label = {
                Text("Page2")
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
            selected = currentSection == HomeSection.Page3,
            onClick = {
                onItemClicked.invoke(HomeSection.Page3)
            },
            label = {
                Text("Page3")
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