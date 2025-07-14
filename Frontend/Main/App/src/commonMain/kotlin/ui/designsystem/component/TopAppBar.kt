package ui.designsystem.component

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.screen.home.CommonTopAppBar

@Composable
fun TopAppBar(
    titleText: String = "Account",
    modifier: Modifier = Modifier,
    onSearch: (() -> Unit)? = null,
    onCloseSearch: (() -> Unit)? = null,
    onAdd: (() -> Unit)? = null,
    onAddMedia: (() -> Unit)? = null,
) {
    CommonTopAppBar(
        modifier = modifier,
        titleText = titleText,
    ) {
        onSearch?.let { Search(onClick = it) }
        onCloseSearch?.let { CloseSearch(onClick = it) }
        onAdd?.let { Add(onClick = it) }
        onAddMedia?.let { AddMedia(onClick = it) }
    }
}

@Preview
@Composable
private fun TopAppBarPreview() {
    Surface {
        TopAppBar(
            onSearch = {},
            onCloseSearch = {},
            onAdd = {},
            onAddMedia = {},
        )
    }
}