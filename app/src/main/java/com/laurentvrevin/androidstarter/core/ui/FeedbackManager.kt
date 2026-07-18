package com.laurentvrevin.androidstarter.core.ui

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Manager global pour diffuser des événements de feedback (ex: Snackbars) 
 * à travers toute l'application.
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
