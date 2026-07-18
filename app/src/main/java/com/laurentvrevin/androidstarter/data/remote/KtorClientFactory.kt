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
     * Configure le client HTTP Ktor avec les meilleurs paramètres de production.
     * 
     * - Engine: OkHttp (standard Android)
     * - ContentNegotiation: JSON via Kotlinx Serialization
     * - Logging: Logs complets pour le debug
     * - Timeouts: Sécurité contre les appels bloqués
     */
    fun create(): HttpClient {
        return HttpClient(OkHttp) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    encodeDefaults = true
                })
            }

            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }

            defaultRequest {
                // Configurer l'URL de base ici si nécessaire
                // url("https://api.example.com/")
            }
        }
    }
}
