package service.session.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import service.session.db.entity.SessionEntity

@Dao
interface SessionDao {
    @Query("SELECT * FROM sessions WHERE id = 1")
    suspend fun getSession(): SessionEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SessionEntity)
    
    @Query("DELETE FROM sessions")
    suspend fun clearAllSessions()
}
