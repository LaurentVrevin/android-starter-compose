package com.laurentvrevin.androidstarter.feature.sample

import androidx.compose.runtime.Immutable
import com.laurentvrevin.androidstarter.core.model.SampleItem
import com.laurentvrevin.androidstarter.designsystem.ui.UiText

@Immutable
data class SampleUiState(
    val items: List<SampleItem> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: UiText? = null
)
