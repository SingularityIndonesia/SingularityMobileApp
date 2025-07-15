package service.vault.web

import model.VaultDocument
import service.vault.web.request.CatalogRequest

interface VaultWebApiClient {
    suspend fun newDocument(): Result<VaultDocument>
    suspend fun catalogue(request: CatalogRequest): Result<List<VaultDocument>>
}

