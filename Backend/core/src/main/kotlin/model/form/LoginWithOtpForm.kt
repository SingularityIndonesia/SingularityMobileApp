package model.form

import kotlinx.serialization.Serializable
import utils.MPAIResponsibility

@Serializable
data class LoginWithOtpForm(
    @MPAIResponsibility
    val formHeader: FormHeader? = null,
    val bffToken: String,
    val email: String,
) {
    companion object {
        fun bffProto(bffToken: String, email: String): LoginWithOtpForm {
            return LoginWithOtpForm(bffToken = bffToken, email = email)
        }
    }
}
