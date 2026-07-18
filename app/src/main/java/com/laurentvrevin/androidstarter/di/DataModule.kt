package com.laurentvrevin.androidstarter.di

import androidx.room.Room
import com.laurentvrevin.androidstarter.data.local.AppDatabase
import com.laurentvrevin.androidstarter.data.local.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    
    // Database
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    // DataStore
    single { PreferenceManager(androidContext()) }

    // DAOs
    single { get<AppDatabase>().userDao() }
}
