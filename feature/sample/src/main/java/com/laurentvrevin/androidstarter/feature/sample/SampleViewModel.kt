package com.laurentvrevin.androidstarter.feature.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laurentvrevin.androidstarter.data.network.NetworkResult
import com.laurentvrevin.androidstarter.data.repository.SampleRepository
import com.laurentvrevin.androidstarter.designsystem.ui.UiText
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SampleViewModel(
    private val repository: SampleRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SampleUiState())
    val state: StateFlow<SampleUiState> = _state.asStateFlow()

    init {
        observeSamples()
        refresh()
    }

    private fun observeSamples() {
        repository.getSamples()
            .onEach { items ->
                _state.update { it.copy(items = items, isLoading = false) }
            }
            .launchIn(viewModelScope)
    }

    fun refresh() {
        viewModelScope.launch {
            _state.update { it.copy(isRefreshing = true, error = null) }
            
            when (val result = repository.refreshSamples()) {
                is NetworkResult.Success -> {
                    _state.update { it.copy(isRefreshing = false) }
                }
                is NetworkResult.Error -> {
                    _state.update { 
                        it.copy(
                            isRefreshing = false, 
                            error = UiText.DynamicString("Erreur: ${result.error.name}")
                        )
                    }
                }
            }
        }
    }
}
