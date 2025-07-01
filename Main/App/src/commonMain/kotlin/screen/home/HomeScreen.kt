package screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import pane.Universe

@Preview
@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(0) { 4 }

    Scaffold(
        topBar = {
            HomeTopAppBar(
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
    ) {
        HorizontalPager(
            modifier = Modifier.padding(it),
            state = pagerState
        ) { pageIndex ->
            when (pageIndex) {
                0 -> Universe(
                    modifier = Modifier.fillMaxSize()
                )

                1 -> Text(
                    modifier = Modifier.fillMaxSize(),
                    text = "Page1"
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