package infra.auth

import model.form.LoginWithOtpForm

interface AuthDB {
    companion object {
        private var instance: AuthDB? = null

        suspend fun Instance(): AuthDB {
            if (instance == null) {
                instance = AuthDBImpl()
            }

            (instance as? AuthDBImpl)?.initializeDatabase()

            return instance!!
        }

        private var testInstance: AuthDB? = null

        suspend fun TestInstance(): AuthDB {
            if (instance == null) {
                instance = AuthDBImpl()
            }

            (instance as? AuthDBImpl)?.initializeInMemoryDatabase()

            return instance!!
        }
    }

    suspend fun register(form: LoginWithOtpForm): Result<Unit>
    suspend fun getExistingFormByEmail(email: String): Result<LoginWithOtpForm?>
}

