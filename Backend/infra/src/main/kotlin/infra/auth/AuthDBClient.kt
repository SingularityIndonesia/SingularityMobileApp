package infra.auth

import kotlinx.serialization.json.Json

internal class AuthDBClient internal constructor() : AuthDB {
    private val Json = Json { prettyPrint = true }

}