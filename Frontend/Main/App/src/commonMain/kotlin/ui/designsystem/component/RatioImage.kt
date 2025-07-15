package ui.designsystem.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage

@Composable
fun RatioImage(
    model: Any,
    modifier: Modifier = Modifier,
    maxHeight: Dp
) {
    val imageAspectRatio = remember { mutableStateOf(1f) }
    AsyncImage(
        modifier = Modifier
            .height(maxHeight)
            .aspectRatio(imageAspectRatio.value)
            .then(modifier),
        model = model,
        contentDescription = "Selected media",
        contentScale = ContentScale.Crop,
        onSuccess = { success ->
            val width = success.result.image.width
            val height = success.result.image.height
            imageAspectRatio.value = width / height.toFloat()
        }
    )
}