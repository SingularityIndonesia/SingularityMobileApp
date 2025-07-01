package component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import designsystem.DesignToken

data class PostItemDisplay(
    val id: String = "",
    val avatarUrl: String = "https://raw.githubusercontent.com/SingularityIndonesia/SingularityIndonesia/refs/heads/main/Logo%20Of%20Singularity%20Indonesia%20%C2%A92023%20Stefanus%20Ayudha.png",
    val postTime: String = "",
    val userName: String = "",
    val postDate: String = "",
    val likeCount: Long = 0L,
    val message: String = "",
    val imageUrls: List<String> = emptyList()
) {
    fun isHaveImages() = imageUrls.isNotEmpty()
    fun isHaveMessage() = message.isNotBlank()
}

@Composable
fun PostItem(
    item: PostItemDisplay,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Row(
        modifier = modifier
            .padding(contentPadding),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Avatar(item.avatarUrl)
        Column {
            if (item.isHaveMessage()) {
                Text(item.message)
            }
            if (item.isHaveImages()) {
                PostImages(item.imageUrls)
            }
            Text("Likes: ${item.id}")
        }
    }
}

@Composable
fun Avatar(
    url: String,
    modifier: Modifier = Modifier,
) {
    val attr = DesignToken.current
    AsyncImage(
        model = url,
        contentDescription = "Avatar",
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .border(BorderStroke(1.dp, attr.color.onSurface), CircleShape)
            .then(modifier),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PostImages(
    imageUrls: List<String>,
    modifier: Modifier = Modifier,
) {
    val attr = DesignToken.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, attr.color.onSurface))
            .then(modifier)
    )
}