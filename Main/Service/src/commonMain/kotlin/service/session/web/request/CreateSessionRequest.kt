package service.session.web.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionRequest(
    val token: String
)
