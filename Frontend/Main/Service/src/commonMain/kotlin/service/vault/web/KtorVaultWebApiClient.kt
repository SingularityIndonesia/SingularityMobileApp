package service.vault.web

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import model.VaultDocument
import service.vault.web.request.CatalogRequest
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

    override suspend fun catalogue(request: CatalogRequest): Result<List<VaultDocument>> {
        return runCatching {
            val response = httpClient.get("vault/documents") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            check(response.status.isSuccess()) {
                throw Exception("Failed to create new document: ${response.status}")
            }

            response.body<List<VaultDocument>>()
        }
    }
}