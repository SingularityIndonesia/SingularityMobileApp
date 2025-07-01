package component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import designsystem.DesignToken

data class PostItemDisplay(
    val id: String = "",
    val avatarUrl: String = "",
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
    val attr = DesignToken.current

    Column(
        modifier = modifier
            .border(BorderStroke(1.dp, attr.color.onSurface))
            .padding(contentPadding)
    ) {
        Row {
            Avatar(item.avatarUrl)
        }
        if (item.isHaveImages()) {
            PostImages(item.imageUrls)
        }
        Text("Likes: ${item.likeCount}")
        if (item.isHaveMessage()) {
            Text(item.message)
        }
    }
}

@Composable
fun Avatar(
    url: String,
    modifier: Modifier = Modifier,
) {
    val attr = DesignToken.current
    Box(
        modifier = Modifier
            .size(60.dp)
            .border(BorderStroke(1.dp, attr.color.onSurface), CircleShape)
            .then(modifier)
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