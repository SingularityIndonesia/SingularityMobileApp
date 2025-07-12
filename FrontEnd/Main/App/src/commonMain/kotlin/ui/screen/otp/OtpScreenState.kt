package ui.screen.otp

data class OtpScreenState(
    val otp: String = "123456",
    val enableOtpInputBuffering: Boolean = false,
    val otpError: String? = null,
    val isLoading: Boolean = false,
    val isTextInputEnabled: Boolean = true,
    val isSubmitButtonEnabled: Boolean = true,
)