package com.laurentvrevin.androidstarter.data.remote

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object KtorClientFactory {

    /**
     * Configure le client HTTP Ktor.
     */
    fun create(config: NetworkConfig): HttpClient {
        return HttpClient(OkHttp) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = config.isDebug
                    isLenient = true
                    encodeDefaults = true
                })
            }

            if (config.isDebug) {
                install(Logging) {
                    logger = Logger.ANDROID
                    level = config.logLevel
                }
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }

            defaultRequest {
                url(config.baseUrl)
            }
        }
    }
}
