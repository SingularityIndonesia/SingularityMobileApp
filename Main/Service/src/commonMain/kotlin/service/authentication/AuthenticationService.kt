package service.authentication

import model.particle.AuthenticationToken
import model.particle.Email
import model.particle.Otp
import service.authentication.web.AuthenticationWebApiClient

class AuthenticationService(
    private val webApi: AuthenticationWebApiClient
) {
    suspend fun requestOtp(email: Email): Result<Unit> {
        return webApi.requestOtp(email)
    }

    suspend fun authenticateByOtp(email: Email, otp: Otp): Result<AuthenticationToken> {
        return webApi.authenticateByOtp(email, otp)
    }
}
