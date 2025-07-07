package model

import model.particle.ImageType
import model.particle.LatLng

data class Image(
    val model: Any,
    val type: ImageType,
    val ratio: Float = 1f,
    val desc: String? = null,
    val timeSignature: Long? = null,
    val locationSignature: LatLng? = null,
)