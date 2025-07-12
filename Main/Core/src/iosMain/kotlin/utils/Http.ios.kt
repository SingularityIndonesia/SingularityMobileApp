package utils

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import platform.Foundation.NSLog

actual fun defaultHttpClient(webHostUrl: String): HttpClient {
    return HttpClient(Darwin) {
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
                    NSLog("KtorHttp", message)
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

        install(HttpTimeout) {
            requestTimeoutMillis = 60_000
            connectTimeoutMillis = 60_000
            socketTimeoutMillis = 60_000
        }

        engine {

        }
    }
}