package utils

import androidx.room.RoomDatabase

expect inline fun <reified T : RoomDatabase> getDatabaseBuilder(dbName: String): RoomDatabase.Builder<T>