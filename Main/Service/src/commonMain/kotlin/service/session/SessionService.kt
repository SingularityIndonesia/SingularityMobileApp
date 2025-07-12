package service.session

import model.particle.AuthenticationToken
import service.session.db.entity.SessionEntity
import service.session.web.SessionWebApiClient

class SessionService(val sessionDB: SessionDB, val sessionApiClient: SessionWebApiClient) {
    suspend fun start(authenticationToken: AuthenticationToken) {
        val session = createSession(authenticationToken)
        sessionDB.saveToken(authenticationToken)
    }

    private suspend fun createSession(authenticationToken: AuthenticationToken): Result<SessionEntity> {
        TODO()
    }
}