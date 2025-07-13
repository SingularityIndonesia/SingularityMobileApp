package bff.model

import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse<T : Any>(
    val success: Boolean,
    val message: String,
    val data: T
)