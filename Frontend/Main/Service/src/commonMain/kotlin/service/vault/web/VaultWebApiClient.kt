package service.vault.web

import model.VaultDocument

interface VaultWebApiClient {
    suspend fun newDocument(): Result<VaultDocument>
}

