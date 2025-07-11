package com.singularityuniverse.singularity.android.utils

import EnvironmentProperties
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun defaultHttpClient() = HttpClient(Android) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        })
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.INFO
    }

    defaultRequest {
        // Replace with your actual API base URL
        url(EnvironmentProperties.WEB_HOST_URL)
        headers.append("Accept", ContentType.Application.Json.toString())
        headers.append("Content-Type", ContentType.Application.Json.toString())
    }

    engine {
        // Android-specific engine configuration
        connectTimeout = 60_000
        socketTimeout = 60_000
    }
}