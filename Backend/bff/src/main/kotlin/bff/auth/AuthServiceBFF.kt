package bff.auth

import bff.BFF_INFRA_TOKEN
import bff.model.request.LoginRequest
import bff.model.response.LoginResponse
import bff.model.response.SuccessResponse
import bff.utils.badRequest
import bff.utils.commonErrorHandling
import bff.utils.success
import io.ktor.server.request.*
import io.ktor.server.routing.*
import model.form.LoginWithOtpForm
import mpai.MPAI
import mpai.auth.requestLoginWithOtp
import utils.isValidEmail
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

    val loginFormProto = LoginWithOtpForm.bffProto(email = request.email)

    MPAI.requestLoginWithOtp(BFF_INFRA_TOKEN, loginFormProto)
        .map {
            // Call BFF service
            SuccessResponse(
                success = true,
                message = "OTP sent to ${request.email}",
                data = LoginResponse(it)
            )
        }.onSuccess {
            return success(it)
        }.onFailure {
            return commonErrorHandling(it)
        }
}
