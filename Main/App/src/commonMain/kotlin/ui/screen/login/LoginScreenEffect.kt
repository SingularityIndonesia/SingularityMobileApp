package ui.screen.login

sealed class LoginScreenEffect {
    data object NavigateToOtp : LoginScreenEffect()
    data class ShowError(val message: String) : LoginScreenEffect()
}