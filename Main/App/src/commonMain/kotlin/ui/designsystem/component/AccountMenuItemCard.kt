package ui.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import ui.designsystem.DarkThemePalette

data class AccountMenuItemDisplay(
    val title: String,
    val subtitle: String? = null,
    val iconRes: DrawableResource? = null,
    val isHighlighted: Boolean = false,
) {
    fun withHighlight(highlighted: Boolean): AccountMenuItemDisplay {
        return copy(isHighlighted = highlighted)
    }

    fun withSearchQuery(query: String): AccountMenuItemDisplay {
        val shouldHighlight = query.isNotEmpty() &&
                (title.contains(query, ignoreCase = true) ||
                        subtitle?.contains(query, ignoreCase = true) == true)
        return copy(isHighlighted = shouldHighlight)
    }
}

@Composable
fun AccountMenuItemCard(
    menuItem: AccountMenuItemDisplay,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    searchQuery: String = "",
    trailingActions: (@Composable RowScope.() -> Unit)? = null
) {
    AccountMenuItemCard(
        modifier = modifier,
        contentPadding = contentPadding,
        leadingIcon = {
            if (menuItem.iconRes != null) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(menuItem.iconRes),
                    contentDescription = menuItem.title,
                    tint = if (menuItem.isHighlighted)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        title = {
            Text(
                text = highlightSearchText(menuItem.title, searchQuery),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                color = if (menuItem.isHighlighted)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface
            )
        },
        subtitle = {
            if (menuItem.subtitle != null) {
                Text(
                    text = highlightSearchText(menuItem.subtitle, searchQuery),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        trailingActions = trailingActions
    )
}

@Composable
fun AccountMenuItemCard(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    leadingIcon: (@Composable RowScope.() -> Unit)? = null,
    title: (@Composable ColumnScope.() -> Unit)? = null,
    subtitle: (@Composable ColumnScope.() -> Unit)? = null,
    trailingActions: (@Composable RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .padding(contentPadding)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Leading Icon Section
        leadingIcon?.invoke(this)

        // Content Section
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            title?.invoke(this)
            subtitle?.invoke(this)
        }

        // Trailing Actions Section
        trailingActions?.invoke(this)
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


// region Previews
object AccountMenuItemDisplayDummy {
    fun settings(highlighted: Boolean = false) = AccountMenuItemDisplay(
        title = "Account Settings",
        subtitle = "Privacy, security, and more",
        iconRes = Res.drawable.ic_person,
        isHighlighted = highlighted
    )

    fun signOut(highlighted: Boolean = false) = AccountMenuItemDisplay(
        title = "Sign Out",
        subtitle = null,
        iconRes = Res.drawable.ic_more_horz,
        isHighlighted = highlighted
    )

    fun help(highlighted: Boolean = false) = AccountMenuItemDisplay(
        title = "Help & Support",
        subtitle = "Get help and contact support",
        iconRes = null,
        isHighlighted = highlighted
    )
}

@Preview
@Composable
private fun AccountMenuItemCardPreview() {
    MaterialTheme(
        DarkThemePalette
    ) {
        Surface {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AccountMenuItemCard(
                    menuItem = AccountMenuItemDisplayDummy.settings(),
                )

                AccountMenuItemCard(
                    menuItem = AccountMenuItemDisplayDummy.signOut(),
                )

                AccountMenuItemCard(
                    menuItem = AccountMenuItemDisplayDummy.help(),
                )
            }
        }
    }
}

@Preview
@Composable
private fun AccountMenuItemCardWithSearchPreview() {
    MaterialTheme(
        DarkThemePalette
    ) {
        Surface {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AccountMenuItemCard(
                    menuItem = AccountMenuItemDisplayDummy.settings().withSearchQuery("settings"),
                    searchQuery = "settings"
                )

                AccountMenuItemCard(
                    menuItem = AccountMenuItemDisplayDummy.help().withSearchQuery("help"),
                    searchQuery = "help"
                )
            }
        }
    }
}

@Preview
@Composable
private fun AccountMenuItemCardHighlightedPreview() {
    MaterialTheme(
        DarkThemePalette
    ) {
        Surface {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AccountMenuItemCard(
                    menuItem = AccountMenuItemDisplayDummy.settings(highlighted = true),
                )

                AccountMenuItemCard(
                    menuItem = AccountMenuItemDisplayDummy.signOut(highlighted = false),
                )
            }
        }
    }
}

@Preview
@Composable
private fun AccountMenuItemCardCustomPreview() {
    MaterialTheme(
        DarkThemePalette
    ) {
        Surface {
            AccountMenuItemCard(
                modifier = Modifier.padding(16.dp),
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(Res.drawable.ic_person),
                        contentDescription = "Custom",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                },
                title = {
                    Text(
                        text = "Custom Menu Item",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                subtitle = {
                    Text(
                        text = "This is a custom implementation",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                trailingActions = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(Res.drawable.ic_more_horz),
                        contentDescription = "More options"
                    )
                }
            )
        }
    }
}
// endregion
