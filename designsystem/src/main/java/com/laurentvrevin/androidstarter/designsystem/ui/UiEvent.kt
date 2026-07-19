package com.laurentvrevin.androidstarter.designsystem.ui

/**
 * Interface scellée pour les événements "One-shot" (événements éphémères).
 */
sealed interface UiEvent {
    data class ShowSnackbar(val message: String, val type: SnackbarType = SnackbarType.Default) : UiEvent
    data object NavigateBack : UiEvent
    data class NavigateTo(val route: String) : UiEvent
}

enum class SnackbarType {
    Default,
    Success,
    Error,
    Warning
}
