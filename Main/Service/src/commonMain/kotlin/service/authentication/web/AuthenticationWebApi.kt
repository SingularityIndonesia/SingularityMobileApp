package service.authentication.web

import model.particle.AuthenticationToken
import model.particle.Email
import model.particle.Otp

interface AuthenticationWebApi {
    suspend fun requestOtp(email: Email): Result<Unit>
    suspend fun authenticateByOtp(email: Email, otp: Otp): Result<AuthenticationToken>
}
