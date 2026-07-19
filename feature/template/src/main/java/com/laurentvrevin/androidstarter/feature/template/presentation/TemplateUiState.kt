package com.laurentvrevin.androidstarter.feature.template.presentation

import androidx.compose.runtime.Immutable
import com.laurentvrevin.androidstarter.designsystem.ui.UiText
import com.laurentvrevin.androidstarter.feature.template.domain.TemplateItem

@Immutable
data class TemplateUiState(
    val items: List<TemplateItem> = emptyList(),
    val isInitialLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val error: UiText? = null,
)
