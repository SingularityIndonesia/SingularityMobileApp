package infra.form.model

import kotlinx.serialization.Serializable

@Serializable
data class FormHeader(
    val uuid: String,
    val type: FormType,
    val status: String,
    val createdAtEpoch: Long,
    val validUntilEpoch: Long,
    val amendVersion: Int,
    val amendTimeSignature: List<Long>,
)