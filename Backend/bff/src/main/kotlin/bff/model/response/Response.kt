package bff.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Response<T : Any>(
    val success: Boolean,
    val data: T,
    val message: String,
)