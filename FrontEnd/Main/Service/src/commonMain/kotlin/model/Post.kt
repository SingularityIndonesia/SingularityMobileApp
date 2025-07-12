package model

import model.particle.ID
import model.particle.Message

data class Post(
    val id: ID,
    val images: List<Image>,
    val message: Message,
    val mentions: List<ID>,
    val createTime: Long,
)