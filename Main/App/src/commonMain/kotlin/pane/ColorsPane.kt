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
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ColorsPane(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val postItems = remember {
        (0..10).map {
            PostItemDisplay(
                id = it.toString(),
            )
        }
    }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = contentPadding
    ) {
        items(postItems) {
            PostItem(
                modifier = Modifier.fillMaxWidth(),
                item = it,
            )
        }
    }
}