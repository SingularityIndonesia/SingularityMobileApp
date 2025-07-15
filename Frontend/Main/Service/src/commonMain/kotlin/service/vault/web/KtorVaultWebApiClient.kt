package service.vault.web

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import model.VaultDocument
import model.particle.AuthenticationToken
import service.session.db.entity.SessionEntity
import service.session.web.SessionWebApiClient
import service.session.web.request.CreateSessionRequest
import service.session.web.response.CreateSessionResponse
import utils.runCatching

class KtorVaultWebApiClient(private val httpClient: HttpClient) : VaultWebApiClient {
    override suspend fun newDocument(): Result<VaultDocument> {
        return runCatching(Dispatchers.IO) {
            val response = httpClient.get("vault/document/new") {
                contentType(ContentType.Application.Json)
            }

            check(response.status.isSuccess()) {
                throw Exception("Failed to create new document: ${response.status}")
            }

            response.body<VaultDocument>()
        }
    }
}