package utils

import android.util.Log
import com.pluto.plugins.network.interceptors.ktor.PlutoKtorInterceptor
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
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

        install(PlutoKtorInterceptor)

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