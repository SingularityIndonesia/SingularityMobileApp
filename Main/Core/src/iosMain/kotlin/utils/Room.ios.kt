package utils

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSHomeDirectory

actual inline fun <reified T : RoomDatabase> getDatabaseBuilder(dbName: String): RoomDatabase.Builder<T> {
    val dbFilePath = getDatabasePath(dbName)
    return Room.databaseBuilder<T>(
        name = dbFilePath
    )
}

@OptIn(ExperimentalForeignApi::class)
fun getDatabasePath(dbName: String): String {
    val documentsDirectory = NSHomeDirectory() + "/Documents"
    return "$documentsDirectory/$dbName"
}
