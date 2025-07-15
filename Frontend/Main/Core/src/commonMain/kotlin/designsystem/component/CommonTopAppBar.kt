package designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopAppBar(
    titleText: String? = null,
    subTitle: String? = null,
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
            BackSymbolic { onNavigateBack.invoke() }
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            titleText?.let {
                TitleLargeText(it)
            }
            subTitle?.let {
                LabelSmallText(it)
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (trailingActions != null) {
                trailingActions()
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CommonTopAppBar(
        titleText = "Home",
        subTitle = "June 12, 2025"
    ) {
        CloseSearchSymbolic {  }
    }
}