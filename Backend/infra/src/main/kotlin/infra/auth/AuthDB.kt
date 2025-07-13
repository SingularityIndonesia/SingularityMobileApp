package infra.auth

import infra.auth.config.DatabaseConfig
import infra.auth.table.AuthTables
import kotlinx.coroutines.Dispatchers
import model.form.LoginWithOtpForm
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import utils.runCatching

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

    suspend fun initializeDatabase(): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            DatabaseConfig.init()
            newSuspendedTransaction {
                SchemaUtils.create(AuthTables)
            }
        }
    }

    suspend fun initializeInMemoryDatabase(): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            DatabaseConfig.initH2ForTesting()

            newSuspendedTransaction {
                SchemaUtils.create(AuthTables)
            }
        }
    }

    suspend fun register(form: LoginWithOtpForm): Result<Unit>
    suspend fun getExistingFormByEmail(email: String): Result<LoginWithOtpForm?>
}

