package ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.pane.AccountPane
import ui.pane.ColorsPane
import ui.pane.MemoriesPane
import utils.OnBackHandler

@Preview
@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(0) { 4 }

    OnBackHandler {

    }

    Scaffold(
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

        Surface {
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                contentPadding = safeContentPadding
            ) { pageIndex ->
                when (pageIndex) {
                    0 -> ColorsPane(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    )

                    1 -> MemoriesPane(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp, start = 8.dp, end = 8.dp)
                    )

                    2 -> Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "Page2"
                    )

                    3 -> AccountPane(
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}