package designsystem.component

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TopAppBar(
    titleText: String = "Account",
    subTitle: String? = null,
    modifier: Modifier = Modifier,
    onSearch: (() -> Unit)? = null,
    onCloseSearch: (() -> Unit)? = null,
    onAdd: (() -> Unit)? = null,
    onAddMedia: (() -> Unit)? = null,
) {
    CommonTopAppBar(
        modifier = modifier,
        titleText = titleText,
        subTitle = subTitle
    ) {
        onSearch?.let { SearchSymbolic(onClick = it) }
        onCloseSearch?.let { CloseSearchSymbolic(onClick = it) }
        onAdd?.let { AddSymbolic(onClick = it) }
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