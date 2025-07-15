package service.vault.web

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import model.Response
import service.vault.web.request.CatalogRequest
import service.vault.web.response.VaultDocumentResponse
import utils.runCatching

class KtorVaultWebApiClient(private val httpClient: HttpClient) : VaultWebApiClient {
    override suspend fun newDocument(): Result<VaultDocumentResponse> {
        return runCatching(Dispatchers.IO) {
            val response = httpClient.get("vault/document/new") {
                contentType(ContentType.Application.Json)
            }

            check(response.status.isSuccess()) {
                throw Exception("Failed to create new document: ${response.status}")
            }

            val resp = response.body<Response<VaultDocumentResponse>>()

            check(resp.success && resp.data != null) {
                throw Exception(resp.error)
            }

            resp.data
        }
    }

    override suspend fun catalogue(request: CatalogRequest): Result<List<VaultDocumentResponse>> {
        return runCatching {
            val response = httpClient.get("vault/documents") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            check(response.status.isSuccess()) {
                throw Exception("Failed to create new document: ${response.status}")
            }

            val resp = response.body<Response<List<VaultDocumentResponse>>>()

            check(resp.success && resp.data != null) {
                throw Exception(resp.error)
            }

            resp.data
        }
    }
}
