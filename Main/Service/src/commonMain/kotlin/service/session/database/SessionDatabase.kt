package service.session.database

import androidx.room.Database
import androidx.room.RoomDatabase
import service.session.dao.SessionDao
import service.session.entity.SessionEntity
import utils.getDatabaseBuilder

@Database(
    entities = [SessionEntity::class],
    version = 1,
    exportSchema = true
)
abstract class SessionDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
}

fun getSessionDatabase(): SessionDatabase {
    return getDatabaseBuilder<SessionDatabase>("session.db")
        .fallbackToDestructiveMigration(true)
        .build()
}
