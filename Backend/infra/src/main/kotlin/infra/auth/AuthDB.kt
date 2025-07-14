package infra.auth

import model.form.LoginWithOtpForm

interface AuthDB {
    companion object {
        private var instance: AuthDB? = null

        suspend fun Instance(): AuthDB {
            if (instance == null) {
                val authDBClient = AuthDBClient()
                authDBClient.initializeDatabase().getOrThrow()
                instance = authDBClient
            }
            return instance!!
        }

        private var testInstance: AuthDB? = null

        suspend fun TestInstance(): AuthDB {
            if (testInstance == null) {
                val authDBClient = AuthDBClient()
                authDBClient.initializeTestingDatabase().getOrThrow()
                testInstance = authDBClient
            }
            return testInstance!!
        }
    }

    suspend fun register(form: LoginWithOtpForm): Result<Unit>
    suspend fun getExistingFormByEmail(email: String): Result<LoginWithOtpForm?>
}

