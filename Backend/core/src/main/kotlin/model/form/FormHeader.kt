package model.form

import kotlinx.serialization.Serializable

@Serializable
data class FormHeader(
    val id: String,
    val type: FormType,
    val status: FormStatus,
    val validUntilEpoch: Long,
)