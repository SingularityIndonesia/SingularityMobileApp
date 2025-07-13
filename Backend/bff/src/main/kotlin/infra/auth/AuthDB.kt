package infra.auth

import kotlinx.coroutines.Dispatchers
import mpai.model.form.LoginWithOtpForm
import utils.runCatching

object AuthDB {
    // Dummy database
    private val LoginFormDB = mutableListOf<LoginWithOtpForm>()

    suspend fun register(form: LoginWithOtpForm): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            check(LoginFormDB.firstOrNull { it.id == form.id } == null)

            LoginFormDB.add(form)
        }
    }
}