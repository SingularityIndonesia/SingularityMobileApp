package bff.model

import kotlinx.serialization.Serializable

/**
 * Response data class for login OTP request
 */
@Serializable
data class LoginOtpResponse(
    val success: Boolean,
    val message: String,
    val token: String
)