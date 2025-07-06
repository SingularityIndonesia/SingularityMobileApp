package model

import model.particle.ID
import model.Image

data class Post(
    val images: List<Image>,
    val message: String,
    val mentions: List<ID>
)