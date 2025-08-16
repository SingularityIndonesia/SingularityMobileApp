package ui.screen.otp

sealed class OtpScreenEffect {
    data class ShowError(val message: String) : OtpScreenEffect()
    data object NavigateToHome : OtpScreenEffect()
}