package infra.auth

import io.ktor.util.date.*
import kotlinx.coroutines.Dispatchers
import model.form.LoginWithOtpForm
import utils.runCatching

object AuthDB {
    // Dummy database
    private val LoginFormDB = mutableListOf<LoginWithOtpForm>()

    suspend fun register(form: LoginWithOtpForm): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            // remove previous form if exist
            LoginFormDB.removeIf { it.body.email == form.body.email }
            LoginFormDB.add(form)
        }
    }

    suspend fun getExistingFormByEmail(email: String): Result<LoginWithOtpForm> {
        return runCatching(Dispatchers.IO) {
            LoginFormDB.first { it.body.email == email }
        }
    }
}