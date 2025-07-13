package bff.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: String
)