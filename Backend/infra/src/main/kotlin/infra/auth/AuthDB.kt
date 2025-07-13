package infra.auth

import kotlinx.coroutines.Dispatchers
import model.form.LoginWithOtpForm
import utils.runCatching

object AuthDB {
    // Dummy database
    private val LoginFormDB = mutableListOf<LoginWithOtpForm>()

    suspend fun register(form: LoginWithOtpForm): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            check(LoginFormDB.firstOrNull { it.header?.id == form.header?.id } == null)

            // remove previous form if exist
            LoginFormDB.removeIf { it.body.email == form.body.email }
            LoginFormDB.add(form)
        }
    }
}