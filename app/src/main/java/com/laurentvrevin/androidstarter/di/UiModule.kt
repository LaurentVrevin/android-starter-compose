package com.laurentvrevin.androidstarter.di

import com.laurentvrevin.androidstarter.core.ui.FeedbackManager
import org.koin.dsl.module

val uiModule = module {
    single { FeedbackManager() }
}
