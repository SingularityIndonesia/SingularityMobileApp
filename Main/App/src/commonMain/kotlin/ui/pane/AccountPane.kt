package ui.pane

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import main.app.generated.resources.Res
import main.app.generated.resources.compose_multiplatform
import main.app.generated.resources.ic_person
import main.app.generated.resources.ic_palette
import main.app.generated.resources.ic_gallery
import main.app.generated.resources.ic_search
import main.app.generated.resources.ic_more_horz

data class AccountMenuItem(
    val title: String,
    val subtitle: String? = null,
    val iconRes: org.jetbrains.compose.resources.DrawableResource,
    val onClick: () -> Unit = {},
    val showDivider: Boolean = false
)

data class UserProfile(
    val name: String,
    val email: String,
    val profileImageUrl: String? = null,
    val storageUsed: String = "2.3 GB",
    val totalStorage: String = "15 GB",
    val storageProgress: Float = 0.15f
)

@Composable
fun AccountPane(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    userProfile: UserProfile = UserProfile(
        name = "John Doe",
        email = "john.doe@example.com"
    ),
    onProfileClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {}
) {
    val accountMenuItems = remember {
        listOf(
            AccountMenuItem(
                title = "Account Settings",
                subtitle = "Privacy, security, and more",
                iconRes = Res.drawable.ic_person,
                onClick = onSettingsClick
            ),
            AccountMenuItem(
                title = "Storage",
                subtitle = "${userProfile.storageUsed} of ${userProfile.totalStorage} used",
                iconRes = Res.drawable.ic_gallery
            ),
            AccountMenuItem(
                title = "Privacy & Security",
                subtitle = "Control your data and privacy",
                iconRes = Res.drawable.ic_palette,
                onClick = onPrivacyClick
            ),
            AccountMenuItem(
                title = "Notifications",
                subtitle = "Manage your notification preferences",
                iconRes = Res.drawable.ic_search
            ),
            AccountMenuItem(
                title = "Data & Storage",
                subtitle = "Network usage, auto-download",
                iconRes = Res.drawable.ic_gallery,
                showDivider = true
            ),
            AccountMenuItem(
                title = "Help & Support",
                subtitle = "Get help and contact support",
                iconRes = Res.drawable.ic_search,
                onClick = onHelpClick
            ),
            AccountMenuItem(
                title = "About",
                subtitle = "App info and legal",
                iconRes = Res.drawable.compose_multiplatform
            ),
            AccountMenuItem(
                title = "Sign Out",
                subtitle = null,
                iconRes = Res.drawable.ic_more_horz,
                onClick = onSignOutClick
            )
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // User Profile Section
        item {
            UserProfileCard(
                userProfile = userProfile,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = onProfileClick
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Storage Usage Section
        item {
            StorageUsageCard(
                userProfile = userProfile,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Account Menu Items
        items(accountMenuItems) { menuItem ->
            AccountMenuItemCard(
                menuItem = menuItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            
            if (menuItem.showDivider) {
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun UserProfileCard(
    userProfile: UserProfile,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (userProfile.profileImageUrl != null) {
                    AsyncImage(
                        model = userProfile.profileImageUrl,
                        contentDescription = "Profile picture",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = userProfile.name.take(1).uppercase(),
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // User Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = userProfile.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = userProfile.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Arrow Icon
            Icon(
                painter = painterResource(Res.drawable.ic_more_horz),
                contentDescription = "View profile",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun StorageUsageCard(
    userProfile: UserProfile,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Storage",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${userProfile.storageUsed} of ${userProfile.totalStorage}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Progress Bar
            LinearProgressIndicator(
                progress = { userProfile.storageProgress },
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Manage storage",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun AccountMenuItemCard(
    menuItem: AccountMenuItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = menuItem.onClick
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
                    text = menuItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (menuItem.subtitle != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = menuItem.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Arrow Icon
            Icon(
                painter = painterResource(Res.drawable.ic_more_horz),
                contentDescription = "Navigate to ${menuItem.title}",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview
@Composable
private fun AccountPanePreview() {
    MaterialTheme {
        AccountPane(
            contentPadding = PaddingValues(16.dp),
            userProfile = UserProfile(
                name = "Jane Smith",
                email = "jane.smith@example.com",
                storageUsed = "4.2 GB",
                totalStorage = "15 GB",
                storageProgress = 0.28f
            )
        )
    }
}

@Preview
@Composable
private fun UserProfileCardPreview() {
    MaterialTheme {
        UserProfileCard(
            userProfile = UserProfile(
                name = "Alex Johnson",
                email = "alex.johnson@example.com"
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun StorageUsageCardPreview() {
    MaterialTheme {
        StorageUsageCard(
            userProfile = UserProfile(
                name = "Test User",
                email = "test@example.com",
                storageUsed = "8.7 GB",
                totalStorage = "15 GB",
                storageProgress = 0.58f
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}
