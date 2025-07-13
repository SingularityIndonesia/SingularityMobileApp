package infra.auth.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object AuthTables : IntIdTable("login_forms") {
    val email = varchar("email", 255).uniqueIndex()
    val headerData = text("header_data").nullable() // JSON string for FormHeader
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}