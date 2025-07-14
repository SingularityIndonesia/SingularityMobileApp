package utils

import platform.Foundation.*

actual fun date(): String {
    val formatter = NSDateFormatter()
    formatter.dateFormat = "MMMM d, yyyy"
    formatter.locale = NSLocale.localeWithLocaleIdentifier("en_US")
    
    return formatter.stringFromDate(NSDate())
}