package model.particle

import kotlin.jvm.JvmInline

@JvmInline
value class Email(val email: String) {
    fun isValid(): Boolean {
        // fixme: apply email validation regex
        return true
    }
}