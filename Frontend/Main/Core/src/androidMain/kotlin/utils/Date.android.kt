package utils

import java.text.SimpleDateFormat
import java.util.*

actual fun date(): String {
    val formatter = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
    return formatter.format(Date())
}