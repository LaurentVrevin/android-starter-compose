package com.laurentvrevin.androidstarter

import android.app.Application
import com.laurentvrevin.androidstarter.di.dataModule
import com.laurentvrevin.androidstarter.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule, dataModule)
        }
    }
}
