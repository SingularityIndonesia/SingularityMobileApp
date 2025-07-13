package mpai.auth

import infra.auth.AuthDB
import io.ktor.util.date.*
import mpai.MPAI
import mpai.model.form.LoginWithOtpForm
import java.util.*
import kotlin.time.Duration.Companion.minutes

suspend fun MPAI.requestLoginWithOtp(form: LoginWithOtpForm): Result<LoginWithOtpForm> {
    return runCatching {
        val newForm = form.copy(
            id = UUID.randomUUID().toString(),
            timeoutEpoch = getTimeMillis() + 5.minutes.inWholeMilliseconds
        )

        AuthDB.register(newForm).getOrThrow()

        newForm
    }
}
