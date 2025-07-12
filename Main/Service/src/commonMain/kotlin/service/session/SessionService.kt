package service.session

import model.particle.AuthenticationToken

class SessionService(val sessionDB: SessionDB) {
    suspend fun start(authenticationToken: AuthenticationToken) {
        sessionDB.saveToken(authenticationToken)
    }
}