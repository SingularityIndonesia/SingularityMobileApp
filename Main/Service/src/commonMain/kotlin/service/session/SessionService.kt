package service.session

import model.particle.AuthenticationToken

class SessionService(val sessionDB: SessionDB) {
    suspend fun start(authenticationToken: AuthenticationToken) {
        TODO("save token to sessionDB")
    }
}