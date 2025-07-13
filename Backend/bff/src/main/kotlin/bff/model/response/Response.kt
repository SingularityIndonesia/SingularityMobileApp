package bff.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Response<T : Any>(
    val success: Boolean,
    val data: T?,
    val error: String?,
    val message: String?,
)