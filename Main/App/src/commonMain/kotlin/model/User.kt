package model

import model.particle.Email
import model.particle.ID
import model.Image
import model.particle.Name

data class User(
    val id: ID,
    val email: Email,
    val basic: BasicInfo,
) {
    data class BasicInfo(
        val fullName: Name,
        val nickname: Name?,
        val profilePicture: Image?
    )
}