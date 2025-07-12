package service.session.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.ktor.util.date.getTimeMillis

@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey val id: Int = 1, // Single session approach
    val token: String,
    val timestamp: Long = getTimeMillis()
)
