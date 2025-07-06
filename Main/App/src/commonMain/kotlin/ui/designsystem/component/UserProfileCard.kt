package ui.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import main.app.generated.resources.Res
import main.app.generated.resources.ic_more_horz
import model.User
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

data class UserProfileDisplay(
    val name: String,
    val email: String,
    val profileImageUrl: String? = null,
    val storageUsed: String = "2.3 GB",
    val totalStorage: String = "15 GB",
    val storageProgress: Float = 0.15f
) {
    companion object {
        fun from(user: User): UserProfileDisplay = UserProfileDisplay(
            name = user.basic.nickname?.name ?: user.basic.fullName.name,
            email = user.email.email,
            profileImageUrl = user.basic.profilePicture?.model as? String,
        )
    }

    // fun composeWith(storageStatus: StorageStatus): UserProfileDisplay {
    //     return copy(
    //         storageUsed = storageStatus.storageUsed,
    //         totalStorage = storageStatus.totalStorage,
    //         storageProgress = storageStatus.storageProgress,
    //     )
    // }
}

@Composable
fun UserProfileCard(
    userProfile: UserProfileDisplay,
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

@Preview
@Composable
private fun UserProfileCardPreview() {
    MaterialTheme {
        UserProfileCard(
            userProfile = UserProfileDisplay(
                name = "Alex Johnson",
                email = "alex.johnson@example.com"
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun UserProfileCardWithImagePreview() {
    MaterialTheme {
        UserProfileCard(
            userProfile = UserProfileDisplay(
                name = "Jane Smith",
                email = "jane.smith@example.com",
                profileImageUrl = "https://example.com/profile.jpg"
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}
