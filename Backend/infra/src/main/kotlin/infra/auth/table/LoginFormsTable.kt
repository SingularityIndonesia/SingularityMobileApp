package infra.auth.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object LoginFormsTable : IntIdTable("login_forms") {
    val email = varchar("email", 255).uniqueIndex()
    val headerData = text("header").nullable() // JSON string for FormHeader
    val bodyData = text("body").nullable() // JSON string for FormBody
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}