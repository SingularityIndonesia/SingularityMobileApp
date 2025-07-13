package bff.model

import kotlinx.serialization.Serializable

/**
 * Response data class for login OTP request
 */
@Serializable
data class TokenResponse(
    val token: String
)