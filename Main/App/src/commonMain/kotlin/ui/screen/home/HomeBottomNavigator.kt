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
            .getOrElse { HomeSection.COLORS }
    )

    BottomAppBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            selected = currentSection == HomeSection.COLORS,
            onClick = {
                onItemClicked.invoke(HomeSection.COLORS)
            },
            label = {
                Text(HomeSection.COLORS.name)
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
            selected = currentSection == HomeSection.MEMORIES,
            onClick = {
                onItemClicked.invoke(HomeSection.MEMORIES)
            },
            label = {
                Text(HomeSection.MEMORIES.name)
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
            selected = currentSection == HomeSection.SEARCH,
            onClick = {
                onItemClicked.invoke(HomeSection.SEARCH)
            },
            label = {
                Text(HomeSection.SEARCH.name)
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
            selected = currentSection == HomeSection.ACCOUNT,
            onClick = {
                onItemClicked.invoke(HomeSection.ACCOUNT)
            },
            label = {
                Text(HomeSection.ACCOUNT.name)
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