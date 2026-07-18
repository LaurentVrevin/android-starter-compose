package com.laurentvrevin.androidstarter.core.ui

import androidx.compose.runtime.Immutable

/**
 * Représente l'état complet d'un écran ou d'une vue.
 * 
 * Suit le principe du pattern MVI (Model-View-Intent) pour garantir 
 * une UI prévisible et sans effets de bord.
 */
@Immutable
sealed interface UiState<out T> {
    data object Idle : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}
