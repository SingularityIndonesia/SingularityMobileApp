package infra.form.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object FormsTable : IntIdTable("forms") {
    val uuid = text("uuid").uniqueIndex()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
    val validUntil = datetime("valid_until").defaultExpression(CurrentDateTime)
    val header = text("form_header").nullable()
    val body = text("form_body").nullable()
}