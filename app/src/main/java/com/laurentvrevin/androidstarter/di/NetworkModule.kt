package com.laurentvrevin.androidstarter.di

import com.laurentvrevin.androidstarter.data.remote.KtorClientFactory
import org.koin.dsl.module

val networkModule = module {
    single { KtorClientFactory.create() }
}
