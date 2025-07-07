package ui.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import model.Image
import model.User
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.designsystem.DarkThemePalette
import utils.initialName

data class UserProfileDisplay(
    val name: String,
    val email: String,
    val initialName: String,
    val profileImageUrl: String? = null,
) {
    companion object {
        fun from(user: User): UserProfileDisplay = UserProfileDisplay(
            name = user.basic.nickname?.name ?: user.basic.fullName.name,
            initialName = (user.basic.nickname?.name ?: user.basic.fullName.name).initialName(),
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
fun UserProfile(
    userProfile: UserProfileDisplay,
    modifier: Modifier = Modifier,
) {
    UserProfile(
        modifier = modifier,
        avatar = {
            if (userProfile.profileImageUrl == null)
                AvatarImage(
                    modifier = Modifier.size(64.dp),
                    initialName = userProfile.initialName
                )
            else {
                AvatarImage(
                    modifier = Modifier.size(64.dp),
                    image = remember(userProfile.profileImageUrl) {
                        Image.from(url = userProfile.profileImageUrl)
                    }
                )
            }
        },
        userName = {
            Text(
                text = userProfile.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
        },
        email = {
            Text(
                text = userProfile.email,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        actions = {
            Edit {}
        }
    )
}

@Composable
fun UserProfile(
    modifier: Modifier = Modifier,
    avatar: (@Composable RowScope.() -> Unit)? = null,
    userName: (@Composable ColumnScope.() -> Unit)? = null,
    email: (@Composable ColumnScope.() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null
) {
    Box(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            avatar?.invoke(this)

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                userName?.invoke(this)
                email?.invoke(this)
            }

            actions?.invoke(this)
        }
    }
}

@Preview
@Composable
private fun UserProfileCardPreview() {
    MaterialTheme(
        DarkThemePalette
    ) {
        Surface {
            UserProfile(
                userProfile = UserProfileDisplay(
                    name = "Alex Johnson",
                    initialName = "AJ",
                    email = "alex.johnson@example.com"
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun UserProfileCardWithImagePreview() {
    MaterialTheme(
        DarkThemePalette
    ) {
        Surface {
            UserProfile(
                userProfile = UserProfileDisplay(
                    name = "Jane Smith",
                    email = "jane.smith@example.com",
                    initialName = "JS",
                    profileImageUrl = "https://example.com/profile.jpg"
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
