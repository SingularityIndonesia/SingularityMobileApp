package pane

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.PostItem
import component.PostItemDisplay

@Composable
fun Universe(
    modifier: Modifier = Modifier
) {
    val postItems = remember {
        (0..100).map {
            PostItemDisplay(
                id = it.toString(),
            )
        }
    }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(postItems) {
            PostItem(
                modifier = Modifier.fillMaxWidth(),
                item = it,
            )
        }
    }
}