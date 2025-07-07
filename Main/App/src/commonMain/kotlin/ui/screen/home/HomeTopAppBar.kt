package ui.screen.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import main.app.generated.resources.Res
import main.app.generated.resources.ic_search
import ui.designsystem.component.Search

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    titleText: String = "Home",
    modifier: Modifier = Modifier,
    showSearchIcon: Boolean = false,
    onSearchClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(titleText)
        },
        actions = {
            if (showSearchIcon) {
                Search(onClick = onSearchClick)
            }
        }
    )
}