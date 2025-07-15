package bff.model

import io.ktor.util.date.*
import kotlinx.serialization.Serializable
import java.util.*

// FIXME: move to infra
@Serializable
data class VaultDocument(
    val id: String = UUID.randomUUID().toString(),
    val dateCreated: Long = getTimeMillis(),
    val content: Content = Content()
) {
    @Serializable
    data class Content(
        val title: String = "Untitled",
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