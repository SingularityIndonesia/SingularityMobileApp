package model

import model.particle.ImageType
import model.particle.LatLng

data class Image(
    val model: Any,
    val type: ImageType,
    val ratio: Float,
    val desc: String?,
    val timeSignature: Long?,
    val locationSignature: LatLng,
)