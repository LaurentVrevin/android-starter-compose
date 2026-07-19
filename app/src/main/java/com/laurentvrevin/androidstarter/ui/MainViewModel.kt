package com.laurentvrevin.androidstarter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laurentvrevin.androidstarter.data.local.AppPreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val preferences: AppPreferences
) : ViewModel() {

    val isDarkTheme: StateFlow<Boolean?> = preferences.isDarkTheme
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun toggleTheme(enabled: Boolean) {
        viewModelScope.launch {
            preferences.setDarkTheme(enabled)
        }
    }
}
