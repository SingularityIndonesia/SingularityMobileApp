package bff.model

import kotlinx.serialization.Serializable

// FIXME: move to infra
@Serializable
data class VaultDocument(
    val id: String,
    val dateCreated: Long,
    val content: Content,
) {
    @Serializable
    data class Content(
        val title: String,
        val media: List<Media>,
    )

    @Serializable
    data class Media(
        val type: MediaType,
        val uri: String,
    )

    @Serializable
    enum class MediaType {
        IMAGE, VIDEO
    }
}