package bff.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String
)