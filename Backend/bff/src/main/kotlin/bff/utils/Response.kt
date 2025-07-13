package bff.utils

import bff.model.response.Response
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
suspend fun badRequest(error: String) {
    context.call.respond(
        status = HttpStatusCode.BadRequest,
        message = Response(
            success = false,
            message = "Speak English! Sincere: Punk.",
            data = error
        )
    )
}

context(context: RoutingContext, route: Route)
suspend fun internalServerError(error: String) {
    context.call.respond(
        status = HttpStatusCode.InternalServerError,
        message = Response(
            success = false,
            message = "It's your fault obviously. Sincere: Karen.",
            data = error
        )
    )
}

context(context: RoutingContext, route: Route)
suspend fun notFound() {
    context.call.respond(
        status = HttpStatusCode.NotFound,
        message = Response(
            success = false,
            message = "It's your fault obviously. Sincere: Karen.",
            data = "There's nothing here, go away.\nSincere: Punk."
        )
    )
}

context(context: RoutingContext, route: Route)
suspend inline fun <reified T : Any> success(response: T, message: String = "") {
    context.call.respond(
        HttpStatusCode.OK,
        Response(
            success = true,
            message = message,
            data = response
        )
    )
}