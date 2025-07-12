package service.session

import model.particle.AuthenticationToken
import service.session.db.entity.SessionEntity
import service.session.web.SessionWebApiClient

class SessionService(val sessionDB: SessionDB, val sessionApiClient: SessionWebApiClient) {
    suspend fun start(authenticationToken: AuthenticationToken): Result<SessionEntity> {
        val session = createSession(authenticationToken)
            .onSuccess {
                sessionDB.saveSession(it)
            }

        return session
    }

    private suspend fun createSession(authenticationToken: AuthenticationToken): Result<SessionEntity> {
        TODO()
    }
}