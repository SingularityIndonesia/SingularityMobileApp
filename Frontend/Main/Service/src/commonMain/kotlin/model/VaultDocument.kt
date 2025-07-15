package model

import io.ktor.util.date.*

data class VaultDocument(
    val id: String = "",
    val dateCreated: Long = getTimeMillis(),
    val content: Content = Content()
) {
    suspend fun addMedia(uris: List<String>) {
        TODO()
    }

    suspend fun removeMedia(uri: String) {
        TODO()
    }

    suspend fun setTitle(title: String) {
        TODO("Not yet implemented")
    }

    data class Content(
        val title: String = "",
        val story: String = "",
        val media: List<Media> = emptyList()
    )

    data class Media(
        val type: MediaType = MediaType.IMAGE,
        val uri: String = ""
    )

    enum class MediaType {
        IMAGE, VIDEO
    }
}
