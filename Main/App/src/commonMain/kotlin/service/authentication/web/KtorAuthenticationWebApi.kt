package service.authentication.web

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import model.particle.AuthenticationToken
import model.particle.Email
import model.particle.Otp
import service.authentication.web.request.AuthenticateByOtpRequest
import service.authentication.web.request.RequestOtpRequest
import service.authentication.web.response.AuthenticateByOtpResponse
import utils.runCatching

class KtorAuthenticationWebApi(
    private val httpClient: HttpClient
) : AuthenticationWebApi {

    override suspend fun requestOtp(email: Email): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            val response = httpClient.post("auth/request-otp") {
                contentType(ContentType.Application.Json)
                setBody(RequestOtpRequest(email.email))
            }

            check(response.status.isSuccess()) {
                throw Exception("Failed to request OTP: ${response.status}")
            }
        }
    }

    override suspend fun authenticateByOtp(email: Email, otp: Otp): Result<AuthenticationToken> {
        return runCatching(Dispatchers.IO) {
            val response = httpClient.post("auth/authenticate") {
                contentType(ContentType.Application.Json)
                setBody(AuthenticateByOtpRequest(email.email, otp.otp))
            }

            check(response.status.isSuccess()) {
                throw Exception("Failed to authenticate: ${response.status}")
            }

            val authResponse: AuthenticateByOtpResponse = response.body()
            AuthenticationToken(authResponse.token)
        }
    }
}

