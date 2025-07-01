package pane

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomePane() {
    val pagerState = rememberPagerState(0) { 4 }

    HorizontalPager(
        state = pagerState
    ) { pageIndex ->
        when (pageIndex) {
            0 -> Universe(
                modifier = Modifier.fillMaxSize()
            )
            1 -> Text("Page 1")
            2 -> Text("Page 2")
            3 -> Text("Page 3")
        }
    }
}