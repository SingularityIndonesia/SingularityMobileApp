package infra.form

import infra.form.model.*
import infra.form.table.FormsTable
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update
import utils.runCatching

class FormDBClient : FormDB {
    private val json = Json { prettyPrint = true }

    private fun <T : Form> formDecode(formType: FormType, proto: String): Result<T> {
        return runCatching {
            when (formType) {
                FormType.LOGIN_FORM -> {
                    @Suppress("UNCHECKED_CAST")
                    json.decodeFromString<LoginForm>(proto) as T
                }
            }
        }
    }

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

                        // Use the separate formDecode function for reification
                        header?.type?.let { formType ->
                            // Reconstruct the complete form JSON structure
                            val bodyJson = body?.let { json.parseToJsonElement(it) }
                            val completeForm = LoginForm(
                                uuid = uuid,
                                header = header,
                                body = bodyJson?.let { json.decodeFromString<LoginFormBody>(body) }
                            )
                            val completeFormJson = json.encodeToString(completeForm)
                            formDecode<T>(formType, completeFormJson).getOrNull()
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
