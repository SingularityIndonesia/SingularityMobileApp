package model

import model.particle.ImageType
import model.particle.LatLng
import org.jetbrains.compose.resources.DrawableResource

data class Image(
    val model: Any,
    val type: ImageType,
    val ratio: Float = 1f,
    val desc: String? = null,
    val timeSignature: Long? = null,
    val locationSignature: LatLng? = null,
)

fun painterImage(drawableRes: DrawableResource): Image {
    return Image(
        model = drawableRes,
        type = ImageType.DrawableResID,
        ratio = 1f,
    )
}