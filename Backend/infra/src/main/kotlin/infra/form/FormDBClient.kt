package infra.form

import infra.form.model.Form
import infra.form.model.FormHeader
import infra.form.model.FormType
import infra.form.model.LoginForm
import infra.form.model.LoginFormBody
import infra.form.table.FormsTable
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import utils.runCatching

class FormDBClient : FormDB {
    private val json = Json { prettyPrint = true }

    override suspend fun <T : Form> getFormByUUID(uuid: String): Result<T?> {
        return runCatching(Dispatchers.IO) {
            newSuspendedTransaction(Dispatchers.IO) {
                FormsTable
                    .selectAll()
                    .where { FormsTable.uuid eq uuid }
                    .singleOrNull()
                    ?.let { row ->
                        val header = row[FormsTable.header]?.let {
                            json.decodeFromString<FormHeader>(it)
                        }
                        val body = row[FormsTable.body]

                        // Based on FormType, reconstruct the appropriate Form
                        when (header?.type) {
                            FormType.LOGIN_FORM -> {
                                val loginBody = body?.let { json.decodeFromString<LoginFormBody>(it) }
                                @Suppress("UNCHECKED_CAST")
                                LoginForm(
                                    uuid = uuid,
                                    header = header,
                                    body = loginBody
                                ) as T
                            }

                            null -> null
                        }
                    }
            }
        }
    }

    override suspend fun <T : Form> insert(form: T): Result<T> {
        return runCatching(Dispatchers.IO) {
            newSuspendedTransaction(Dispatchers.IO) {
                val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
                val validUntil = form.header?.validUntilEpoch?.let {
                    Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.UTC)
                } ?: now

                FormsTable.insert {
                    it[uuid] = form.uuid
                    it[createdAt] = now
                    it[updatedAt] = now
                    it[FormsTable.validUntil] = validUntil
                    it[header] = form.header?.let { header -> json.encodeToString(header) }
                    it[body] = form.body?.let { body -> json.encodeToString(body) }
                }

                form
            }
        }
    }

    override suspend fun removeFormByUUID(uuid: String): Result<Unit> {
        return runCatching(Dispatchers.IO) {
            newSuspendedTransaction(Dispatchers.IO) {
                FormsTable.deleteWhere { FormsTable.uuid eq uuid }
            }
        }
    }

    override suspend fun <T : Form> updateFormByUUID(uuid: String, form: T): Result<T> {
        return runCatching(Dispatchers.IO) {
            newSuspendedTransaction(Dispatchers.IO) {
                val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
                val validUntil = form.header?.validUntilEpoch?.let {
                    Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.UTC)
                } ?: now

                FormsTable.update({ FormsTable.uuid eq uuid }) {
                    it[updatedAt] = now
                    it[FormsTable.validUntil] = validUntil
                    it[header] = form.header?.let { header -> json.encodeToString(header) }
                    it[body] = form.body?.let { body -> json.encodeToString(body) }
                }

                form
            }
        }
    }

    override suspend fun isExist(uuid: String): Result<Boolean> {
        return runCatching(Dispatchers.IO) {
            newSuspendedTransaction(Dispatchers.IO) {
                FormsTable
                    .selectAll()
                    .where { FormsTable.uuid eq uuid }
                    .count() > 0
            }
        }
    }
}
