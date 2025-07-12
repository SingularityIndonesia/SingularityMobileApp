package service.session

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import model.particle.AuthenticationToken
import service.session.db.entity.SessionEntity
import service.session.db.getSessionDatabase

class SessionDB {
    private val database = getSessionDatabase()
    private val sessionDao = database.sessionDao()

    suspend fun saveSession(session: SessionEntity) {
        withContext(Dispatchers.IO) {
            sessionDao.insertSession(session)
        }
    }

    suspend fun getToken(): AuthenticationToken? {
        val session = withContext(Dispatchers.IO) {
            sessionDao.getSession()
        }?.let { AuthenticationToken(it.token) }

        return session
    }

    suspend fun clearToken() {
        sessionDao.clearAllSessions()
    }
}
