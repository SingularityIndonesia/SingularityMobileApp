package ui.screen.home

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
import font.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.navigation.Route.HomeSection

@Preview
@Composable
fun HomeBottomBar(
    pagerState: PagerState = rememberPagerState(0) { 4 },
    onItemClicked: (HomeSection) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val currentSection by rememberUpdatedState(
        runCatching { HomeSection.entries[pagerState.currentPage] }
            .getOrElse { HomeSection.Colors }
    )

    BottomAppBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            selected = currentSection == HomeSection.Colors,
            onClick = {
                onItemClicked.invoke(HomeSection.Colors)
            },
            label = {
                Text(HomeSection.Colors.name)
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
            selected = currentSection == HomeSection.Memories,
            onClick = {
                onItemClicked.invoke(HomeSection.Memories)
            },
            label = {
                Text(HomeSection.Memories.name)
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
            selected = currentSection == HomeSection.Search,
            onClick = {
                onItemClicked.invoke(HomeSection.Search)
            },
            label = {
                Text(HomeSection.Search.name)
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
            selected = currentSection == HomeSection.Account,
            onClick = {
                onItemClicked.invoke(HomeSection.Account)
            },
            label = {
                Text(HomeSection.Account.name)
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