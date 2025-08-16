package utils

import java.text.SimpleDateFormat
import java.util.*

actual fun date(): String {
    val formatter = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
    return formatter.format(Date())
}

actual fun dateTime(): String {
    val formatter = SimpleDateFormat("MMMM d, yyyy HH:mm", Locale.ENGLISH)
    return formatter.format(Date())
}

actual fun Long.toDate(): String {
    val formatter = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
    return formatter.format(Date(this))
}

actual fun Long.toDateTime(): String {
    val formatter = SimpleDateFormat("MMMM d, yyyy HH:mm", Locale.ENGLISH)
    return formatter.format(Date(this))
}