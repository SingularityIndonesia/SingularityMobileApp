package model.form

import kotlinx.serialization.Serializable

@Serializable
data class FormHeader(
    val id: String? = null,
    val timeoutEpoch: Long? = null
)