package com.laurentvrevin.androidstarter.core.di

import com.laurentvrevin.androidstarter.core.ui.FeedbackManager
import org.koin.dsl.module

val uiModule = module {
    single { FeedbackManager() }
}
