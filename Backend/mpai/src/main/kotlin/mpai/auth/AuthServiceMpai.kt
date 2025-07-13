package mpai.auth

import infra.auth.AuthDB
import io.ktor.util.date.*
import model.form.FormHeader
import model.form.LoginWithOtpForm
import mpai.MPAI
import java.util.*
import kotlin.time.Duration.Companion.minutes

suspend fun MPAI.requestLoginWithOtp(form: LoginWithOtpForm): Result<LoginWithOtpForm> {
    return runCatching {
        val newForm = form.copy(
            formHeader = FormHeader(
                id = UUID.randomUUID().toString(),
                timeoutEpoch = getTimeMillis() + 5.minutes.inWholeMilliseconds
            )
        )

        AuthDB.register(newForm).getOrThrow()

        newForm
    }
}
