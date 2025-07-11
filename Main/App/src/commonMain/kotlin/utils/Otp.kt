package utils

fun otpIsValid(otp: String): Boolean {
    return otp.length == 6 && runCatching { otp.toInt() }.isSuccess
}