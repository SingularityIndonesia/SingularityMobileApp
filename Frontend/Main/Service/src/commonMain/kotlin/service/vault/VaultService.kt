package service.vault

import model.VaultDocument
import service.vault.web.VaultWebApiClient
import service.vault.web.request.CatalogRequest

class VaultService(
    private val vaultWebApiClient: VaultWebApiClient
) {
    suspend fun newDocument(): Result<VaultDocument> {
        return vaultWebApiClient.newDocument()
    }

    suspend fun catalogue(catalogRequest: CatalogRequest): Result<List<VaultDocument>> {
        return vaultWebApiClient.catalogue(catalogRequest)
    }
}