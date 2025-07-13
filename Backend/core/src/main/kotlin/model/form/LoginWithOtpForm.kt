package model.form

import kotlinx.serialization.Serializable
import utils.MPAIResponsibility

@Serializable
data class LoginWithOtpForm(
    @MPAIResponsibility
    val header: FormHeader? = null,
    val body: LoginWithOtpFormData,
) {
    companion object {
        fun bffProto(email: String): LoginWithOtpForm {
            return LoginWithOtpForm(
                body = LoginWithOtpFormData(email = email)
            )
        }
    }

    @Serializable
    data class LoginWithOtpFormData(
        val email: String
    )
}
