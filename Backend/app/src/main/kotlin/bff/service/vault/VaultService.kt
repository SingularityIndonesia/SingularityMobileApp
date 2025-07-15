package bff.service.vault

import bff.model.VaultDocument
import bff.model.success
import io.ktor.server.routing.*
import io.ktor.util.date.getTimeMillis
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
    return success(
        response = VaultDocument(
            id = UUID.randomUUID().toString(),
            dateCreated = getTimeMillis(),
            content = VaultDocument.Content(),
        )
    )
}