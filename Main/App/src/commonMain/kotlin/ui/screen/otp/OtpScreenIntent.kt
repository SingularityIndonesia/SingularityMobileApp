package ui.screen.otp

sealed class OtpScreenIntent {
    data class UpdateOtp(val otp: String) : OtpScreenIntent()
    data object SubmitOtpVerification : OtpScreenIntent()
}