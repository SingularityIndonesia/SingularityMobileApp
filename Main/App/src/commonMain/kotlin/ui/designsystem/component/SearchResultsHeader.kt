package ui.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SearchResultsHelper(
    resultsCount: Int,
    modifier: Modifier = Modifier,
    title: String = "Search Results"
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "$resultsCount found",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
private fun SearchResultsHeaderPreview() {
    MaterialTheme {
        SearchResultsHelper(
            resultsCount = 5,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun SearchResultsHeaderSingleResultPreview() {
    MaterialTheme {
        SearchResultsHelper(
            resultsCount = 1,
            modifier = Modifier.padding(16.dp)
        )
    }
}
