package bff.auth

import bff.model.LoginOtpResponse
import bff.model.LoginRequest
import bff.utils.badRequest
import bff.utils.commonErrorHandling
import bff.utils.isValidEmail
import bff.utils.success
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import java.util.*

context(route: Route)
fun AuthServiceBFF() {
    with(route) {
        route("/auth") {
            post("/login") {
                requestLoginOtp()
            }
        }
    }
}

context(context: RoutingContext, route: Route)
suspend fun requestLoginOtp() {
    val request = context.call.receive<LoginRequest>()

    check(request.email.isNotBlank()) {
        return badRequest("Missing field required: email")
    }

    check(isValidEmail(request.email)) {
        return badRequest("Invalid email address")
    }

    runCatching {
        // Generate a dummy token (in real implementation, this would be sent via email)
        val token = generateDummyToken()

        // Call BFF service
        LoginOtpResponse(
            success = true,
            message = "OTP sent to ${request.email}",
            token = token
        )
    }.onSuccess {
        return success(Json.encodeToString(it))
    }.onFailure {
        return commonErrorHandling(it)
    }
}

fun generateDummyToken(): String {
    return "dummy_token_${UUID.randomUUID().toString().substring(0, 8)}"
}
