package bff.model.response

import kotlinx.serialization.Serializable
import model.form.LoginWithOtpForm

@Serializable
data class LoginResponse(
    val form: LoginWithOtpForm,
)