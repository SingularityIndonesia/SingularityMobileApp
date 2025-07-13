package infra.auth

import infra.config.DatabaseConnection
import infra.auth.table.LoginFormsTable
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import model.form.FormHeader
import model.form.LoginWithOtpForm
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update
import utils.runCatching

internal class AuthDBImpl internal constructor() : AuthDB {

    internal suspend fun initializeDatabase(): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            DatabaseConnection.connect()
            newSuspendedTransaction {
                SchemaUtils.create(LoginFormsTable)
            }
        }
    }

    internal suspend fun initializeInMemoryDatabase(): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            DatabaseConnection.connectH2ForTesting()

            newSuspendedTransaction {
                SchemaUtils.create(LoginFormsTable)
            }
        }
    }

    override suspend fun register(form: LoginWithOtpForm): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            newSuspendedTransaction(Dispatchers.IO) {
                // Check if user exists and update, otherwise insert
                val existingRow = LoginFormsTable
                    .selectAll()
                    .where { LoginFormsTable.email eq form.body.email }
                    .singleOrNull()

                val now = Clock.System.now().toLocalDateTime(TimeZone.Companion.UTC)

                if (existingRow != null) {
                    // Update existing record
                    LoginFormsTable.update({ LoginFormsTable.email eq form.body.email }) {
                        it[headerData] = form.header?.let { header -> Json.Default.encodeToString(header) }
                        it[updatedAt] = now
                    }
                } else {
                    // Insert new record
                    LoginFormsTable.insert {
                        it[email] = form.body.email
                        it[headerData] = form.header?.let { header -> Json.Default.encodeToString(header) }
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
    override suspend fun getExistingFormByEmail(email: String): Result<LoginWithOtpForm?> {
        return runCatching(Dispatchers.IO) {
            newSuspendedTransaction(Dispatchers.IO) {
                LoginFormsTable
                    .selectAll()
                    .where { LoginFormsTable.email eq email }
                    .singleOrNull()
                    ?.let { row ->
                        val headerData = row[LoginFormsTable.headerData]?.let {
                            Json.Default.decodeFromString<FormHeader>(it)
                        }

                        LoginWithOtpForm(
                            header = headerData,
                            body = LoginWithOtpForm.LoginWithOtpFormData(
                                email = row[LoginFormsTable.email]
                            )
                        )
                    }
            }
        }
    }
}