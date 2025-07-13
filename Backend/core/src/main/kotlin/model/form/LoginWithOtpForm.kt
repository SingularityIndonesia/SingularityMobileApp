package model.form

import kotlinx.serialization.Serializable
import utils.MPAIResponsibility

@Serializable
data class LoginWithOtpForm(
    @MPAIResponsibility
    val formHeader: FormHeader? = null,
    val email: String,
) {
    companion object {
        fun bffProto(email: String): LoginWithOtpForm {
            return LoginWithOtpForm(email = email)
        }
    }
}
