package screen.home

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import main.app.generated.resources.*
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
                Text("Color")
            },
            icon = {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(Res.drawable.ic_color),
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
                Text("Memories")
            },
            icon = {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(Res.drawable.ic_gallery),
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
                Text("Search")
            },
            icon = {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(Res.drawable.ic_search),
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
                Text("Account")
            },
            icon = {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(Res.drawable.ic_person),
                    contentDescription = null
                )
            }
        )

    }
}