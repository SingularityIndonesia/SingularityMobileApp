package ui.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import main.app.generated.resources.Res
import main.app.generated.resources.ic_close
import main.app.generated.resources.ic_delete
import main.app.generated.resources.ic_more_horz
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun OptionsMenu(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_more_horz,
        contentDescription = "OptionsMenu",
        onClick = onClick
    )
}

@Composable
fun Clear(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ActionIcon(
        modifier = modifier,
        drawRes = Res.drawable.ic_close,
        contentDescription = "Clear",
        onClick = onClick
    )
}

@Composable
private fun ActionIcon(
    modifier: Modifier = Modifier,
    drawRes: DrawableResource,
    contentDescription: String,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(drawRes),
            contentDescription = contentDescription
        )
    }
}