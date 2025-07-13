package model.form

import io.ktor.util.date.*
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

    fun isValid() = getTimeMillis() < (header?.validUntilEpoch ?: 0L)

    @Serializable
    data class LoginWithOtpFormData(
        val email: String
    )
}
