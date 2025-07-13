package mpai.auth

import infra.auth.AuthDB
import io.ktor.util.date.*
import model.form.FormHeader
import model.form.FormStatus
import model.form.FormType
import model.form.LoginWithOtpForm
import mpai.MPAI
import java.util.*
import kotlin.time.Duration.Companion.minutes

suspend fun MPAI.requestLoginWithOtp(token: String, form: LoginWithOtpForm): Result<LoginWithOtpForm> {
    return runCatching {
        val newForm = form.copy(
            formHeader = FormHeader(
                id = UUID.randomUUID().toString(),
                type = FormType.REQUEST_LOGIN_WITH_OTP,
                validUntilEpoch = getTimeMillis() + 5.minutes.inWholeMilliseconds,
                status = FormStatus.WAITING_FOR_VERIFICATION
            )
        )

        AuthDB.register(newForm).getOrThrow()

        newForm
    }
}
