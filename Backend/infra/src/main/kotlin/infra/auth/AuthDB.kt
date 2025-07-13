package infra.auth

import kotlinx.coroutines.Dispatchers
import model.form.LoginWithOtpForm
import utils.runCatching

object AuthDB {
    // Dummy database
    private val LoginFormDB = mutableListOf<LoginWithOtpForm>()

    suspend fun register(form: LoginWithOtpForm): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            check(LoginFormDB.firstOrNull { it.formHeader?.id == form.formHeader?.id } == null)

            // remove previous form if exist
            LoginFormDB.removeIf { it.email == form.email }
            LoginFormDB.add(form)
        }
    }
}