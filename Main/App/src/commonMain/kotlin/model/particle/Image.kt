package model.particle

data class Image(
    val model: Any,
    val type: ImageType,
    val ratio: Float,
    val desc: String?,
    val timeSignature: Long?,
    val locationSignature: LatLng,
)

