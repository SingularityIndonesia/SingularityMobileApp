package ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.navigation.HomeSection
import ui.pane.ColorsPane
import ui.pane.MemoriesPane

@Preview
@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(0) { 4 }

    Scaffold(
        topBar = {
            val title by rememberUpdatedState(HomeSection.entries[pagerState.currentPage].name)
            HomeTopAppBar(
                titleText = title,
                modifier = Modifier.fillMaxWidth()
            )
        },
        bottomBar = {
            HomeBottomBar(
                modifier = Modifier.fillMaxWidth(),
                pagerState = pagerState,
                onItemClicked = {
                    scope.launch {
                        pagerState.animateScrollToPage(it.ordinal)
                    }
                }
            )
        }
    ) { safeContentPadding ->

        Surface(
            modifier = Modifier,
        ) {
            HorizontalPager(
                modifier = Modifier.padding(safeContentPadding),
                state = pagerState,
                userScrollEnabled = false,
            ) { pageIndex ->
                when (pageIndex) {
                    0 -> ColorsPane(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    )

                    1 -> MemoriesPane(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp, start = 16.dp, end = 16.dp)
                    )

                    2 -> Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "Page2"
                    )

                    3 -> Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "Page3"
                    )
                }
            }
        }
    }
}