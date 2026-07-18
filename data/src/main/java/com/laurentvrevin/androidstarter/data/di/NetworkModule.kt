package com.laurentvrevin.androidstarter.data.di

import com.laurentvrevin.androidstarter.data.remote.KtorClientFactory
import org.koin.dsl.module

val networkModule = module {
    single { KtorClientFactory.create() }
}
