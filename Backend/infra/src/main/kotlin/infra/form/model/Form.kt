package infra.form.model

import io.ktor.util.date.*

sealed interface Form {
    val uuid: String
    val header: FormHeader?
    val body: FormBody?

    fun isValid(): Boolean = (header?.validUntilEpoch ?: 0L) > getTimeMillis()
}

