package service.vault.web

import service.vault.web.response.VaultDocument
import service.vault.web.request.CatalogRequest

interface VaultWebApiClient {
    suspend fun newDocument(): Result<VaultDocument>
    suspend fun catalogue(request: CatalogRequest): Result<List<VaultDocument>>
}

