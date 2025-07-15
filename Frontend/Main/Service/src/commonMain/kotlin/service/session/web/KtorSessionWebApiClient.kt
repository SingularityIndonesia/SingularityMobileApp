package service.session.web

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import model.particle.AuthenticationToken
import service.session.db.entity.SessionEntity
import service.session.web.request.CreateSessionRequest
import service.session.web.response.CreateSessionResponse
import utils.runCatching

class KtorSessionWebApiClient(private val httpClient: HttpClient) : SessionWebApiClient {
    override suspend fun createSession(authenticationToken: AuthenticationToken): Result<SessionEntity> {
        return runCatching(Dispatchers.IO) {
            val response = httpClient.post("session/create") {
                contentType(ContentType.Application.Json)
                setBody(CreateSessionRequest(authenticationToken.token))
            }

            check(response.status.isSuccess()) {
                throw Exception("Failed to create session: ${response.status}")
            }

            val sessionResponse: CreateSessionResponse = response.body()
            SessionEntity(
                id = sessionResponse.id,
                token = sessionResponse.token,
                timestamp = sessionResponse.timestamp
            )
        }
    }
}