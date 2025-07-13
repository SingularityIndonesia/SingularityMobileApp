package infra.auth.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object LoginFormsTable : IntIdTable("login_forms") {
    val uuid = text("uuid").uniqueIndex()
    val email = varchar("email", 255).uniqueIndex()
    val formData = text("form").nullable() // JSON string for FormHeader
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}