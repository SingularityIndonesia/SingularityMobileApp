package infra.form.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginForm(
    override val uuid: String,
    override val header: FormHeader?,
    override val body: LoginFormBody?
) : Form

@Serializable
data class LoginFormBody(
    val email: String
) : FormBody