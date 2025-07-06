package model

import model.particle.Image

data class User(
    val id: String,
    val email: String,
    val basic: BasicInfo,
) {
    data class BasicInfo(
        val fullName: String,
        val nickname: String?,
        val profilePicture: Image?
    )
}