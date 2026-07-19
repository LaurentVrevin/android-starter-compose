package com.laurentvrevin.androidstarter.designsystem.ui

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Système de communication pour les événements UI éphémères globaux.
 */
class FeedbackManager {
    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    suspend fun emit(event: UiEvent) {
        _events.emit(event)
    }

    suspend fun showSnackbar(message: String, type: SnackbarType = SnackbarType.Default) {
        _events.emit(UiEvent.ShowSnackbar(message, type))
    }
}
