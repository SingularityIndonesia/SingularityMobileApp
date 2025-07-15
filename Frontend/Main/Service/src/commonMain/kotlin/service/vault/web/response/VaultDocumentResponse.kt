package service.vault.web.response

import io.ktor.util.date.getTimeMillis
import kotlinx.serialization.Serializable

@Serializable
data class VaultDocumentResponse(
    val id: String = "",
    val dateCreated: Long = getTimeMillis(),
    val content: Content = Content()
) {

    @Serializable
    data class Content(
        val title: String = "",
        val media: List<Media> = emptyList()
    )

    @Serializable
    data class Media(
        val type: MediaType = MediaType.IMAGE,
        val uri: String = ""
    )

    enum class MediaType {
        IMAGE, VIDEO
    }
}