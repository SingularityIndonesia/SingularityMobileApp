package bff.service.vault

import bff.model.VaultDocument
import bff.model.success
import io.ktor.server.routing.*

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
        response = VaultDocument()
    )
}