package model.form

data class LoginWithOtpForm(
    val bffToken: String,
    val email: String,
    override val id: String? = null,
    override val timeoutEpoch: Long? = null
) : Form() {
    companion object {
        fun bffProto(bffToken: String, email: String): LoginWithOtpForm {
            return LoginWithOtpForm(bffToken, email)
        }
    }
}
