package ui.designsystem.component

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.screen.home.CommonTopAppBar

@Composable
fun TopAppBar(
    onSearch: (() -> Unit)? = null,
    onCloseSearch: (() -> Unit)? = null
) {
    CommonTopAppBar(
        titleText = "Account"
    ) {
        onSearch?.let { Search(onClick = it) }
        onCloseSearch?.let { CloseSearch(onClick = it) }
    }
}

@Preview
@Composable
private fun TopAppBarPreview() {
    Surface {
        TopAppBar(
            onSearch = {},
            onCloseSearch = {}
        )
    }
}