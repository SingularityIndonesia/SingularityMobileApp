package pane

import androidx.compose.foundation.layout.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import main.app.generated.resources.Res
import main.app.generated.resources.ic_brush
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MemoriesPane(
    modifier: Modifier = Modifier,
    onCreateNewMemories: () -> Unit = {},
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val scope = rememberCoroutineScope()
    val dataSource = remember { GalleryItemDisplayPagingSource(scope) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        val contentPadding by rememberUpdatedState(
            PaddingValues(
                start = contentPadding.calculateStartPadding(LayoutDirection.Rtl),
                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
                bottom = 88.dp
            )
        )
        GalleryPane(
            dataSource = dataSource,
            contentPadding = contentPadding
        )
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = onCreateNewMemories
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_brush),
                contentDescription = null
            )
        }
    }
}