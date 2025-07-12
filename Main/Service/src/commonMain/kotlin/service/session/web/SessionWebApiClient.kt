package service.session.web

import model.particle.AuthenticationToken
import service.session.db.entity.SessionEntity

interface SessionWebApiClient {
    suspend fun createSession(authenticationToken: AuthenticationToken): Result<SessionEntity>
}

