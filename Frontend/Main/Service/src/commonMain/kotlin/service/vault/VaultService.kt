package service.vault

import model.VaultDocument
import service.vault.web.VaultWebApiClient

class VaultService(
    private val vaultWebApiClient: VaultWebApiClient
) {
    suspend fun newDocument(): Result<VaultDocument> {
        return vaultWebApiClient.newDocument()
    }
}