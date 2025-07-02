package component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import designsystem.DesignToken

data class PostItemDisplay(
    val id: String = "",
    val avatarUrl: String = "https://raw.githubusercontent.com/SingularityIndonesia/SingularityIndonesia/refs/heads/main/Logo%20Of%20Singularity%20Indonesia%20%C2%A92023%20Stefanus%20Ayudha.png",
    val postTime: String = "2 hours",
    val userName: String = "Singularity",
    val likeCount: Long = 0L,
    val message: String = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque congue dictum elit aliquet rutrum. Fusce neque dolor, tempus nec ultricies feugiat, ultrices vel urna. Sed pulvinar nisl sit amet sem luctus efficitur. Pellentesque ultrices dolor enim, in tincidunt ex hendrerit a. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam feugiat id est vitae auctor. Sed id ultrices nisi, vitae interdum justo.
        Ut ac sodales tortor. Proin eros enim, molestie nec placerat eget, auctor eu ipsum. Mauris massa magna, porta vel purus ut, lobortis ultricies ipsum. Maecenas et sem commodo elit lobortis lobortis. Nulla tempus porttitor sem a iaculis. Cras at commodo sem, sed dictum massa. Mauris augue enim, ornare vitae rutrum dignissim, dignissim vel turpis. Aliquam venenatis tellus vitae nisl vulputate, sit amet mollis ex ultrices. Etiam eget mollis enim. Pellentesque facilisis, nisl eget maximus semper, mi lectus varius turpis, eu sagittis dui quam eget mi. Proin porta mollis ligula, pretium scelerisque dui molestie nec. Nam et convallis nisi, eget pulvinar nisl.
        Cras aliquam accumsan nisl vel blandit. Suspendisse metus orci, posuere nec est sed, consequat blandit tellus. Donec interdum consectetur libero, ut dignissim tortor sodales eu. Phasellus vel hendrerit erat, at mattis risus. Suspendisse eget metus ut erat laoreet egestas. Sed eu sagittis diam, et commodo arcu. Mauris vel pharetra libero, sodales facilisis urna. Nam aliquet ultricies felis id bibendum. Quisque eu justo ac justo congue suscipit. In mollis tempus lorem. Ut pellentesque neque nec risus feugiat placerat. Ut venenatis vel tortor ut maximus.
    """.trimIndent()
        .replace("\n", "")
        .split(". ")
        .shuffled()
        .first() + ".",
    val imageUrls: List<String> = listOf(
        "https://cnc-magazine.oramiland.com/parenting/images/kim-da-hyun.width-800.format-webp.webp",
        "https://pbs.twimg.com/media/GllvhtPW4AAUnRR?format=jpg&name=large",
        "https://cdn.shopify.com/s/files/1/0469/3927/5428/files/TWICE-Dahyun-for-A-pieu-2023-Juicy-Pang-Tint-documents-2.jpg?v=1738754206",
        "https://i.pinimg.com/736x/3f/10/a6/3f10a655fbe33402d858bffa7193df16.jpg"
    ).shuffled()
        .take((1..3).shuffled().first())
) {
    fun isHaveImages() = imageUrls.isNotEmpty()
    fun isHaveMessage() = message.isNotBlank()
}

@Composable
fun PostItem(
    item: PostItemDisplay,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement
            .spacedBy(16.dp)
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Avatar(item.avatarUrl)

            Column(
                modifier = Modifier.align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        item.userName,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = item.postTime,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                if (item.isHaveMessage()) {
                    Text(
                        item.message,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        if (item.isHaveImages()) {
            PostImages(
                imageUrls = item.imageUrls,
                contentPadding = PaddingValues(start = (16 + 36 + 16).dp, end = 16.dp)
            )
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
    contentPadding: PaddingValues = PaddingValues(),
) {
    val attr = DesignToken.current
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = contentPadding
    ) {
        items(imageUrls) { url ->
            AsyncImage(
                model = url,
                contentDescription = url,
                modifier = Modifier
                    .height(250.dp)
                    .aspectRatio(3 / 2f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}