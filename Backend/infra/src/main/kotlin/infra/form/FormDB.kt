package infra.form

import infra.config.DatabaseConnection
import infra.form.model.Form
import infra.form.table.FormsTable
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import utils.runCatching

interface FormDB {
    companion object {
        private var instance: FormDB? = null

        suspend fun Instance(): FormDB {
            if (instance == null) {
                val authDBClient = FormDBClient()
                authDBClient.initializeDatabase().getOrThrow()
                instance = authDBClient
            }
            return instance!!
        }

        private var testInstance: FormDB? = null

        suspend fun TestInstance(): FormDB {
            if (testInstance == null) {
                val authDBClient = FormDBClient()
                authDBClient.initializeTestingDatabase().getOrThrow()
                testInstance = authDBClient
            }
            return testInstance!!
        }
    }

    suspend fun initializeDatabase(): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            DatabaseConnection.connection()
            newSuspendedTransaction {
                SchemaUtils.create(FormsTable)
            }
        }
    }

    suspend fun initializeTestingDatabase(): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            DatabaseConnection.testConnection()
            newSuspendedTransaction {
                SchemaUtils.create(FormsTable)
            }
        }
    }

    suspend fun <T : Form> getFormByUUID(uuid: String): Result<T?>
    suspend fun <T : Form> insert(form: T): Result<T>
    suspend fun removeFormByUUID(uuid: String): Result<Unit>
    suspend fun <T : Form> updateFormByUUID(uuid: String, form: T): Result<T>
}