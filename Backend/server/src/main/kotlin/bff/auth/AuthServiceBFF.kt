package bff.auth

import bff.model.request.LoginRequest
import bff.model.response.SuccessResponse
import bff.model.response.TokenResponse
import bff.utils.badRequest
import bff.utils.commonErrorHandling
import bff.utils.isValidEmail
import bff.utils.success
import io.ktor.server.request.*
import io.ktor.server.routing.*
import java.util.*

context(route: Route)
fun AuthServiceBFF() {
    with(route) {
        route("/auth") {
            post("/request-otp") {
                requestLoginOtp()
            }
        }
    }
}

context(context: RoutingContext, route: Route)
suspend fun requestLoginOtp() {
    val request = context.call.receive<LoginRequest>()

    check(request.email.isNotBlank()) {
        return badRequest("Missing required field: email")
    }

    check(isValidEmail(request.email)) {
        return badRequest("Invalid email address")
    }

    runCatching {
        // Generate a dummy token (in real implementation, this would be sent via email)
        val token = generateDummyToken()

        // Call BFF service
        SuccessResponse(
            success = true,
            message = "OTP sent to ${request.email}",
            data = TokenResponse(token)
        )
    }.onSuccess {
        return success(it)
    }.onFailure {
        return commonErrorHandling(it)
    }
}

fun generateDummyToken(): String {
    return "dummy_token_${UUID.randomUUID().toString().substring(0, 8)}"
}
