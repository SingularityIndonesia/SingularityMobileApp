package service.vault.web.response

import io.ktor.util.date.*
import kotlinx.serialization.Serializable

@Serializable
data class VaultDocument(
    val id: String = "",
    val dateCreated: Long = getTimeMillis(),
    val content: Content = Content()
) {

    @Serializable
    data class Content(
        val title: String = "",
        val mediaUris: List<String> = emptyList()
    )
}