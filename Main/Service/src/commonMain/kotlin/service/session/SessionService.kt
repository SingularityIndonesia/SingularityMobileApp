package service.session

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import model.particle.AuthenticationToken
import service.session.db.entity.SessionEntity
import service.session.web.SessionWebApiClient
import utils.runCatching

class SessionService(val sessionDB: SessionDB, val sessionApiClient: SessionWebApiClient) {
    suspend fun start(authenticationToken: AuthenticationToken): Result<SessionEntity> {
        val session = createSession(authenticationToken)
            .onSuccess {
                sessionDB.saveSession(it)
            }

        return session
    }

    private suspend fun createSession(authenticationToken: AuthenticationToken): Result<SessionEntity> {
        return runCatching(Dispatchers.IO) {
            TODO()
        }
    }
}