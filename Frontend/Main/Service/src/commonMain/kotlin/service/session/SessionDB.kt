package service.session

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import model.particle.AuthenticationToken
import service.session.db.entity.SessionEntity
import utils.runCatching

class SessionDB {
    // private val database = getSessionDatabase()
    // private val sessionDao = database.sessionDao()

    suspend fun saveSession(session: SessionEntity): Result<SessionEntity> {
        return runCatching(Dispatchers.IO) {
            //sessionDao.insertSession(session)
            // session
            TODO()
        }
    }

    suspend fun getToken(): AuthenticationToken? {
        // val session = withContext(Dispatchers.IO) {
        //     sessionDao.getSession()
        // }?.let { AuthenticationToken(it.token) }

        // return session
        TODO()
    }

    suspend fun clearToken(): Result<Unit> {
        // return runCatching(Dispatchers.IO) { sessionDao.clearAllSessions() }
        TODO()
    }
}
