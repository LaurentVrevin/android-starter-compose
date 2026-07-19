package com.laurentvrevin.androidstarter.data.di

import com.laurentvrevin.androidstarter.data.remote.KtorClientFactory
import com.laurentvrevin.androidstarter.data.remote.NetworkConfig
import io.ktor.client.plugins.logging.LogLevel
import org.koin.dsl.module

val networkModule = module {
    
    // Config réseau par défaut
    // En production, on utiliserait BuildConfig.DEBUG et une vraie URL
    single {
        NetworkConfig(
            baseUrl = "https://api.example.com/",
            isDebug = true, // À adapter selon le build type
            logLevel = LogLevel.INFO
        )
    }

    single { KtorClientFactory.create(get()) }
}
