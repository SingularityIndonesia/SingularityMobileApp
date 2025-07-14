package infra.form

import infra.form.model.Form

class FormDBClient : FormDB {
    override suspend fun <T : Form> getFormByUUID(uuid: String): Result<T?> {
        TODO("Not yet implemented")
    }

    override suspend fun <T : Form> insert(form: T): Result<T> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFormByUUID(uuid: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun <T : Form> updateFormByUUID(uuid: String, form: T): Result<T> {
        TODO("Not yet implemented")
    }
}