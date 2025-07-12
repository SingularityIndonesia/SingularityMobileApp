package service.session.web

import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import model.particle.AuthenticationToken
import service.session.db.entity.SessionEntity
import utils.runCatching

class KtorSessionWebApiClient(private val httpClient: HttpClient) : SessionWebApiClient {
    override suspend fun createSession(authenticationToken: AuthenticationToken): Result<SessionEntity> {
        return runCatching(Dispatchers.IO) {
            TODO("target api : session/create")
        }
    }
}