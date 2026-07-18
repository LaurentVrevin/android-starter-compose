package com.laurentvrevin.androidstarter.core.ui

import androidx.compose.runtime.Immutable

/**
 * Interface scellée représentant les différents états possibles d'un écran ou d'un composant.
 */
@Immutable
sealed interface UiState<out T> {
    data object Idle : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}
