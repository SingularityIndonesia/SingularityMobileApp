package utils

import androidx.room.Room
import androidx.room.RoomDatabase

actual inline fun <reified T : RoomDatabase> getDatabaseBuilder(dbName: String): RoomDatabase.Builder<T> {
    val context = RoomDBContextProvider.getContext()
    val dbFile = context.getDatabasePath(dbName)
    return Room.databaseBuilder<T>(
        context = context,
        name = dbFile.absolutePath
    )
}
