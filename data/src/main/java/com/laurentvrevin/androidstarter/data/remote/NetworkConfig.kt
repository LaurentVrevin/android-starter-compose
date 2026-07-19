package com.laurentvrevin.androidstarter.data.remote

import io.ktor.client.plugins.logging.LogLevel

/**
 * Configuration pour le client HTTP Ktor.
 * Permet d'injecter des paramètres différents selon l'environnement (Debug/Release/Test).
 */
data class NetworkConfig(
    val baseUrl: String,
    val isDebug: Boolean = false,
    val logLevel: LogLevel = LogLevel.NONE
)
