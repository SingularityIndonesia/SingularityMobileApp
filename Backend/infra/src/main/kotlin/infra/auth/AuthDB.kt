package infra.auth

import model.form.LoginWithOtpForm

interface AuthDB {
    companion object {
        private var instance: AuthDB? = null

        suspend fun Instance(): AuthDB {
            if (instance == null) {
                val authDBImpl = AuthDBImpl()
                authDBImpl.initializeDatabase().getOrThrow()
                instance = authDBImpl
            }
            return instance!!
        }

        private var testInstance: AuthDB? = null

        suspend fun TestInstance(): AuthDB {
            if (testInstance == null) {
                val authDBImpl = AuthDBImpl()
                authDBImpl.initializeTestingDatabase().getOrThrow()
                testInstance = authDBImpl
            }
            return testInstance!!
        }
    }

    suspend fun register(form: LoginWithOtpForm): Result<Unit>
    suspend fun getExistingFormByEmail(email: String): Result<LoginWithOtpForm?>
}

