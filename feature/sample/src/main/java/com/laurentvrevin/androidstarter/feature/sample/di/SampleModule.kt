package com.laurentvrevin.androidstarter.feature.sample.di

import com.laurentvrevin.androidstarter.feature.sample.SampleViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sampleModule = module {
    viewModel { SampleViewModel(get()) }
}
