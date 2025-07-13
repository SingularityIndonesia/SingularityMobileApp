package bff.utils

import bff.model.ErrorResponse
import io.ktor.http.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

context(context: RoutingContext, route: Route)
suspend fun commonErrorHandling(e: Throwable) {
    when (e) {
        is IllegalStateException, is BadRequestException -> {
            badRequest(e.message ?: "Invalid request")
        }

        else -> {
            internalServerError(e.message ?: "Internal server error")
        }
    }
}

context(context: RoutingContext, route: Route)
suspend fun badRequest(message: String) {
    context.call.respond(
        HttpStatusCode.BadRequest,
        ErrorResponse(message)
    )
}

context(context: RoutingContext, route: Route)
suspend fun internalServerError(message: String) {
    context.call.respond(
        HttpStatusCode.InternalServerError,
        ErrorResponse(message)
    )
}

context(context: RoutingContext, route: Route)
suspend fun notFound() {
    context.call.respond(
        HttpStatusCode.NotFound,
        "There's nothing here, go away."
    )
}

context(context: RoutingContext, route: Route)
suspend fun success(response: String) {
    context.call.respond(
        HttpStatusCode.OK,
        response
    )
}