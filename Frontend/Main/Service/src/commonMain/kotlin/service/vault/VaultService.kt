package service.vault

import service.vault.web.response.VaultDocumentResponse
import service.vault.web.VaultWebApiClient
import service.vault.web.request.CatalogRequest

class VaultService(
    private val vaultWebApiClient: VaultWebApiClient
) {
    suspend fun newDocument(): Result<VaultDocumentResponse> {
        return vaultWebApiClient.newDocument()
    }

    suspend fun catalogue(catalogRequest: CatalogRequest): Result<List<VaultDocumentResponse>> {
        return vaultWebApiClient.catalogue(catalogRequest)
    }
}