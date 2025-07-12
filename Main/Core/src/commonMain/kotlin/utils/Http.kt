package utils

import io.ktor.client.HttpClient

expect fun defaultHttpClient(webHostUrl: String): HttpClient