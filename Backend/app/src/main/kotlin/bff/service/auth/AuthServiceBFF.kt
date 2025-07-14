package bff.service.auth

import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import io.ktor.server.routing.RoutingContext
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import utils.isValidEmail

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
//    val request = context.call.receive<LoginRequest>()
//
//    check(request.email.isNotBlank()) {
//        return badRequest("Missing required field: email.")
//    }
//
//    check(isValidEmail(request.email)) {
//        return badRequest("Invalid email address.")
//    }
//
//    val loginFormProto = LoginWithOtpForm.bffProto(email = request.email)
//
//    MPAI.requestLoginWithOtp(BFF_INFRA_TOKEN, loginFormProto)
//        .map {
//            LoginResponse(it)
//        }.onSuccess {
//            return success(it, "OTP sent to ${request.email}.")
//        }.onFailure {
//            return commonErrorHandling(it)
//        }
}