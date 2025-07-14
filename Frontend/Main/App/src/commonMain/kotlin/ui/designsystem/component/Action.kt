package ui.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import font.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

val LocalIconButtonColor = staticCompositionLocalOf<IconButtonColors?> { null }

@Preview
@Composable
fun OptionsMenu(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_more_horz,
        contentDescription = "OptionsMenu",
        onClick = onClick
    )
}

@Preview
@Composable
fun Edit(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_edit,
        contentDescription = "Edit",
        onClick = onClick
    )
}

@Preview
@Composable
fun Add(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_add,
        contentDescription = "Edit",
        onClick = onClick
    )
}

@Preview
@Composable
fun Delete(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_delete,
        contentDescription = "Dekete",
        onClick = onClick
    )
}

@Preview
@Composable
fun CloseSearch(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_close,
        contentDescription = "CloseSearch",
        onClick = onClick
    )
}

@Preview
@Composable
fun AddMedia(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_add_photo_alternate,
        contentDescription = "CloseSearch",
        onClick = onClick
    )
}

@Preview
@Composable
fun Clear(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_close,
        contentDescription = "Clear",
        onClick = onClick
    )
}

@Preview
@Composable
fun Next(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_next,
        contentDescription = "Next",
        onClick = onClick
    )
}

@Preview
@Composable
fun Back(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_back,
        contentDescription = "Back",
        onClick = onClick
    )
}

@Preview
@Composable
fun SignOut(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_logout,
        contentDescription = "SignOut",
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.Red.copy(alpha = .5f),
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        onClick = onClick
    )
}

@Composable
fun Search(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_search,
        contentDescription = "Search",
        onClick = onClick
    )
}

@Composable
private fun ActionIcon(
    modifier: Modifier = Modifier,
    drawRes: DrawableResource,
    contentDescription: String,
    colors: IconButtonColors = LocalIconButtonColor.current
        ?: IconButtonDefaults.iconButtonColors(),
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        colors = colors
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(drawRes),
            contentDescription = contentDescription
        )
    }
}