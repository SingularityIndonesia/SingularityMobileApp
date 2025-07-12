package service.session.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import service.session.entity.SessionEntity

@Dao
interface SessionDao {
    @Query("SELECT * FROM sessions WHERE id = 1")
    suspend fun getSession(): SessionEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SessionEntity)
    
    @Query("DELETE FROM sessions")
    suspend fun clearAllSessions()
}
