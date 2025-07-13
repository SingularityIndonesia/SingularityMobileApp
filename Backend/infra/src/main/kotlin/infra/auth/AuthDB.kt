package infra.auth

import model.form.LoginWithOtpForm
import kotlinx.coroutines.Dispatchers
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