package com.laurentvrevin.androidstarter.core.ui

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Système de communication pour les événements UI éphémères (One-shot).
 * 
 * Utilise un [SharedFlow] pour diffuser des événements qui ne doivent être
 * traités qu'une seule fois (comme l'affichage d'une Snackbar) et qui ne
 * font pas partie de l'état persistant de l'écran.
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
