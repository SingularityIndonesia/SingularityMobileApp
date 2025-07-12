package service.session

import model.particle.AuthenticationToken
import service.session.db.entity.SessionEntity
import service.session.web.SessionWebApiClient

class SessionService(
    private val sessionDB: SessionDB,
    private val sessionWebApiClient: SessionWebApiClient
) {
    suspend fun start(authenticationToken: AuthenticationToken): Result<SessionEntity> {
        val session = sessionWebApiClient.createSession(authenticationToken)
            .fold(
                onSuccess = {
                    sessionDB.saveSession(it)
                },
                onFailure = {
                    Result.failure(it)
                }
            )

        return session
    }

    suspend fun stop() {

    }
}