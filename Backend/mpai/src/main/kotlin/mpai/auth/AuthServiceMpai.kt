package mpai.auth

import infra.auth.AuthDB
import io.ktor.util.date.*
import model.exception.ExistingFormStillValid
import model.form.FormHeader
import model.form.FormStatus
import model.form.FormType
import model.form.LoginWithOtpForm
import mpai.MPAI
import utils.isValidEmail
import java.util.*
import kotlin.time.Duration.Companion.minutes

suspend fun MPAI.requestLoginWithOtp(token: String, form: LoginWithOtpForm): Result<LoginWithOtpForm> {
    return runCatching {
        check(form.body.email.isNotBlank())
        check(isValidEmail(form.body.email))

        validate(form)?.run { throw this }

        val newForm = form.copy(
            header = FormHeader(
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

private suspend fun validate(form: LoginWithOtpForm): Throwable? {
    return AuthDB.getExistingFormByEmail(form.body.email)
        .mapCatching {
            val formValidUntilEpoch = it.header?.validUntilEpoch ?: 0L
            val isFormStillValid = formValidUntilEpoch > getTimeMillis()

            if (isFormStillValid) {
                throw ExistingFormStillValid()
            }
        }
        .exceptionOrNull()
}