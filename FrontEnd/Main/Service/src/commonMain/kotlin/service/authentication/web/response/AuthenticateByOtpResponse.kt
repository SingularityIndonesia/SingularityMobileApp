package service.authentication.web.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticateByOtpResponse(
    val token: String
)