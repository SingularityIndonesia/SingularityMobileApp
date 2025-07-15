package bff.service.vault

import bff.model.VaultDocument
import bff.model.commonErrorHandling
import bff.model.success
import io.ktor.server.routing.*
import io.ktor.util.date.*
import java.util.*

context(route: Route)
fun VaultServiceBFF() {
    with(route) {
        route("/vault/document") {
            get("/new") {
                requestNewDocument()
            }
        }
    }
}

context(context: RoutingContext, route: Route)
suspend fun requestNewDocument() {
    // fixme: dummy
    runCatching {
        VaultDocument(
            id = UUID.randomUUID().toString(),
            dateCreated = getTimeMillis(),
            content = VaultDocument.Content(
                title = "Untitled",
                media = (0..5).map { VaultDocument.Media(VaultDocument.MediaType.VIDEO, "$it") },
            ),
        )
    }.onSuccess {
        return success(it)
    }.onFailure {
        return commonErrorHandling(it)
    }
}