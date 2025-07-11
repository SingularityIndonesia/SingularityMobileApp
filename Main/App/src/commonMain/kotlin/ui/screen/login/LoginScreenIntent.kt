package ui.screen.login

sealed class LoginScreenIntent {
    data class UpdateEmail(val email: String) : LoginScreenIntent()
    data object SubmitLogin : LoginScreenIntent()
}