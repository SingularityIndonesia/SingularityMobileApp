package infra.auth

import infra.config.DatabaseConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import model.form.LoginWithOtpForm
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update
import utils.runCatching

class AuthDB private constructor() {

    companion object {
        private var instance: AuthDB? = null

        suspend fun Instance(): AuthDB {
            if (instance == null) {
                instance = AuthDB()
                instance!!.initializeDatabase()
            }

            return instance!!
        }

        private var testInstance: AuthDB? = null

        suspend fun TestInstance(): AuthDB {
            if (instance == null) {
                instance = AuthDB()
                instance!!.initializeDatabase()
            }

            return instance!!
        }
    }

    private suspend fun initializeDatabase(): Result<Unit> {
        DatabaseConfig.init()
        return runCatching(Dispatchers.IO) {
            newSuspendedTransaction {
                SchemaUtils.create(LoginFormTable)
            }
        }
    }

    suspend fun register(form: LoginWithOtpForm): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            newSuspendedTransaction {
                // Check if user exists and update, otherwise insert
                val existingRow = LoginFormTable
                    .selectAll()
                    .where { LoginFormTable.email eq form.body.email }
                    .singleOrNull()

                val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)

                if (existingRow != null) {
                    // Update existing record
                    LoginFormTable.update({ LoginFormTable.email eq form.body.email }) {
                        it[headerData] = form.header?.let { header -> Json.encodeToString(header) }
                        it[updatedAt] = now
                    }
                } else {
                    // Insert new record
                    LoginFormTable.insert {
                        it[email] = form.body.email
                        it[headerData] = form.header?.let { header -> Json.encodeToString(header) }
                        it[createdAt] = now
                        it[updatedAt] = now
                    }
                }
            }
        }
    }

    /**
     * must return existing form or null, or exception
     */
    suspend fun getExistingFormByEmail(email: String): Result<LoginWithOtpForm?> {
        return runCatching(Dispatchers.IO) {
            newSuspendedTransaction {
                LoginFormTable
                    .selectAll()
                    .where { LoginFormTable.email eq email }
                    .singleOrNull()
                    ?.let { row ->
                        val headerData = row[LoginFormTable.headerData]?.let {
                            Json.decodeFromString<model.form.FormHeader>(it)
                        }

                        LoginWithOtpForm(
                            header = headerData,
                            body = LoginWithOtpForm.LoginWithOtpFormData(
                                email = row[LoginFormTable.email]
                            )
                        )
                    }
            }
        }
    }
}