package service.session.db

import androidx.room.Database
import androidx.room.RoomDatabase
import service.session.db.dao.SessionDao
import service.session.db.entity.SessionEntity
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
