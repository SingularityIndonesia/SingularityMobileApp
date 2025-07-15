package service.vault.web

import model.Response
import service.vault.web.response.VaultDocumentResponse
import service.vault.web.request.CatalogRequest

interface VaultWebApiClient {
    suspend fun newDocument(): Result<VaultDocumentResponse>
    suspend fun catalogue(request: CatalogRequest): Result<List<VaultDocumentResponse>>
}

