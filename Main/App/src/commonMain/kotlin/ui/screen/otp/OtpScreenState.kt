package ui.screen.otp

data class OtpScreenState(
    val otp: String = "",
    val enableOtpInputBuffering: Boolean = true,
    val otpError: String? = null,
    val isLoading: Boolean = false
)