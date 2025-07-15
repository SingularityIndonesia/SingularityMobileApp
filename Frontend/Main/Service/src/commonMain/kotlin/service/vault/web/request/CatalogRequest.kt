package service.vault.web.request

data class CatalogRequest(
    val page: Int,
    val pageSize: Int,
    val query: String
)