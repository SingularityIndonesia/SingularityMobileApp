package service.session

import io.ktor.util.date.getTimeMillis
import model.particle.AuthenticationToken
import service.session.database.getSessionDatabase
import service.session.entity.SessionEntity

class SessionDB {
    private val database = getSessionDatabase()
    private val sessionDao = database.sessionDao()
    
    suspend fun saveToken(token: AuthenticationToken) {
        val sessionEntity = SessionEntity(
            id = 1,
            token = token.token,
            timestamp = getTimeMillis()
        )
        sessionDao.insertSession(sessionEntity)
    }
    
    suspend fun getToken(): AuthenticationToken? {
        val session = sessionDao.getSession()
        return session?.let { AuthenticationToken(it.token) }
    }
    
    suspend fun clearToken() {
        sessionDao.clearAllSessions()
    }
}
