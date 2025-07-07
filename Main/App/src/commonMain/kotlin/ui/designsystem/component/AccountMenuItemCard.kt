package ui.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import main.app.generated.resources.Res
import main.app.generated.resources.ic_more_horz
import main.app.generated.resources.ic_person
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

data class AccountMenuItemCardDisplay(
    val title: String,
    val subtitle: String? = null,
    val iconRes: DrawableResource,
    val showDivider: Boolean = false
)

@Composable
fun AccountMenuItemCard(
    menuItem: AccountMenuItemCardDisplay,
    modifier: Modifier = Modifier,
    searchQuery: String = ""
) {
    Card(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Surface(
                modifier = Modifier.size(40.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(menuItem.iconRes),
                        contentDescription = menuItem.title,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = highlightSearchText(menuItem.title, searchQuery),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (menuItem.subtitle != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = highlightSearchText(menuItem.subtitle, searchQuery),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Arrow Icon
            OptionsMenu { }
        }
    }
}

@Composable
private fun highlightSearchText(
    text: String,
    searchQuery: String
): androidx.compose.ui.text.AnnotatedString {
    if (searchQuery.isEmpty()) {
        return buildAnnotatedString { append(text) }
    }

    return buildAnnotatedString {
        val startIndex = text.indexOf(searchQuery, ignoreCase = true)
        if (startIndex >= 0) {
            append(text.substring(0, startIndex))
            withStyle(
                style = SpanStyle(
                    background = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append(text.substring(startIndex, startIndex + searchQuery.length))
            }
            append(text.substring(startIndex + searchQuery.length))
        } else {
            append(text)
        }
    }
}

@Preview
@Composable
private fun AccountMenuItemCardPreview() {
    MaterialTheme {
        AccountMenuItemCard(
            menuItem = AccountMenuItemCardDisplay(
                title = "Account Settings",
                subtitle = "Privacy, security, and more",
                iconRes = Res.drawable.ic_person,
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun AccountMenuItemCardWithSearchPreview() {
    MaterialTheme {
        AccountMenuItemCard(
            menuItem = AccountMenuItemCardDisplay(
                title = "Account Settings",
                subtitle = "Privacy, security, and more",
                iconRes = Res.drawable.ic_person,
            ),
            searchQuery = "settings",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun AccountMenuItemCardNoSubtitlePreview() {
    MaterialTheme {
        AccountMenuItemCard(
            menuItem = AccountMenuItemCardDisplay(
                title = "Sign Out",
                subtitle = null,
                iconRes = Res.drawable.ic_more_horz,
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}
