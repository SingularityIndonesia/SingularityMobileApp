package mpai.model.form

abstract class Form {
    open val id: String? = null
    open val timeoutEpoch: Long? = null
}