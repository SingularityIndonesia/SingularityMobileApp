package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VaultDocument(
    @SerialName("id")
    val id: String,
    @SerialName("content")
    val content: Content
) {
    @Serializable
    data class Content(
        @SerialName("title")
        val title: String,
        @SerialName("story")
        val story: String,
        @SerialName("media")
        val media: List<Media>
    )

    @Serializable
    data class Media(
        @SerialName("type")
        val type: MediaType,
        @SerialName("url")
        val url: String
    )

    enum class MediaType {
        IMAGE, VIDEO
    }
}