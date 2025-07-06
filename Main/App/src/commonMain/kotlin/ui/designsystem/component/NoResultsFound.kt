package ui.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import main.app.generated.resources.Res
import main.app.generated.resources.ic_search

@Composable
fun NoResultsFound(
    searchQuery: String,
    modifier: Modifier = Modifier,
    title: String = "No results found",
    description: String? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_search),
            contentDescription = "No results",
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(48.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = description ?: "Try searching for \"$searchQuery\" with different keywords",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
private fun NoResultsFoundPreview() {
    MaterialTheme {
        NoResultsFound(
            searchQuery = "example",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun NoResultsFoundCustomPreview() {
    MaterialTheme {
        NoResultsFound(
            searchQuery = "test",
            title = "No settings found",
            description = "Try searching with different terms",
            modifier = Modifier.padding(16.dp)
        )
    }
}
