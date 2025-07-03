package ui.pane

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.designsystem.component.CreateNewFab

@Composable
fun MemoriesPane(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onCreateNewMemories: () -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val dataSource = remember { GalleryItemDisplayPagingSource(scope) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        val contentPadding by rememberUpdatedState(
            PaddingValues(
                top = 0.dp,
                start = contentPadding.calculateStartPadding(LayoutDirection.Rtl),
                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
                bottom = 88.dp
            )
        )
        GalleryPane(
            dataSource = dataSource,
            contentPadding = contentPadding
        )
        CreateNewFab(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = onCreateNewMemories
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MemoriesPane()
}