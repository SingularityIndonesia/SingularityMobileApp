package service.session.web.response

import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionResponse(
    val id: Int,
    val token: String,
    val timestamp: Long
)
