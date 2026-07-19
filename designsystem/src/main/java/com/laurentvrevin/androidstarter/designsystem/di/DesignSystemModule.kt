package com.laurentvrevin.androidstarter.designsystem.di

import com.laurentvrevin.androidstarter.designsystem.ui.FeedbackManager
import org.koin.dsl.module

val designSystemModule =
    module {
        single { FeedbackManager() }
    }
