package service.authentication.web.request

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticateByOtpRequest(
    val email: String,
    val otp: String
)