package ui.screen.otp

data class OtpScreenState(
    val otp: String = "",
    val otpError: String? = null,
    val isLoading: Boolean = false
)