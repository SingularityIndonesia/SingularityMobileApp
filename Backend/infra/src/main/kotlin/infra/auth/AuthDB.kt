package infra.auth

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

    /**
     * must return existing form or null, or exception
     */
    suspend fun getExistingFormByEmail(email: String): Result<LoginWithOtpForm?> {
        // run catching is a must to catch the IO exception
        return runCatching(Dispatchers.IO) {
            LoginFormDB.firstOrNull { it.body.email == email }
        }
    }
}