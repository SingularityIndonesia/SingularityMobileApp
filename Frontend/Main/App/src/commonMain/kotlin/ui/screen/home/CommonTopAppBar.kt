package ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.designsystem.component.Back
import ui.designsystem.component.CloseSearch
import ui.designsystem.component.TitleLargeText

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopAppBar(
    titleText: String = "Home",
    modifier: Modifier = Modifier.fillMaxWidth(),
    onNavigateBack: (() -> Unit)? = null,
    trailingActions: (@Composable RowScope.() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (onNavigateBack != null) {
            Back { onNavigateBack.invoke() }
        }
        TitleLargeText(
            modifier = Modifier.weight(1f),
            text = titleText,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (trailingActions != null) {
                trailingActions()
            }
        }
    }
}