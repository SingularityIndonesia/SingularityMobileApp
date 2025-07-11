package service.authentication.web.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestOtpRequest(
    val email: String
)