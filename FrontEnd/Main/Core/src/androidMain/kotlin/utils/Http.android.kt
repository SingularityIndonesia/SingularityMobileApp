package utils

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun defaultHttpClient(webHostUrl: String): HttpClient {
    return HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = false
            })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("KtorHttp", message)
                }
            }
            level = LogLevel.ALL
        }

        defaultRequest {
            // Replace with your actual API base URL
            url(webHostUrl)
            headers.append("Accept", ContentType.Application.Json.toString())
            headers.append("Content-Type", ContentType.Application.Json.toString())
        }

        engine {
            // Android-specific engine configuration
            connectTimeout = 60_000
            socketTimeout = 60_000
        }
    }
}