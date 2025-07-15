package utils

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.localeWithLocaleIdentifier

actual fun date(): String {
    val formatter = NSDateFormatter()
    formatter.dateFormat = "MMMM d, yyyy"
    formatter.locale = NSLocale.localeWithLocaleIdentifier("en_US")
    
    return formatter.stringFromDate(NSDate())
}

actual fun dateTime(): String {
    val formatter = NSDateFormatter()
    formatter.dateFormat = "MMMM d, yyyy HH:mm"
    formatter.locale = NSLocale.localeWithLocaleIdentifier("en_US")

    return formatter.stringFromDate(NSDate())
}