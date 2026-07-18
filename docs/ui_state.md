# UI State & Feedback Management

Cette section explique comment gérer les états de vos écrans et les feedbacks utilisateur.

---

## 1. Gestion des États (`UiState`)

Chaque écran doit être piloté par une [`UiState`](../core/src/main/java/com/laurentvrevin/androidstarter/core/ui/UiState.kt).

```kotlin
sealed interface UiState<out T> {
    data object Idle : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}
```

### Pattern recommandé
Dans le ViewModel, utilisez un `StateFlow` pour exposer l'état.

---

## 2. Événements "One-shot" (`UiEvent`)

Pour les actions éphémères (Snackbar, Navigation), on utilise [`UiEvent`](../core/src/main/java/com/laurentvrevin/androidstarter/core/ui/UiEvent.kt) diffusés via le **`FeedbackManager`**.

### Envoyer un événement
```kotlin
feedbackManager.showSnackbar("Enregistré avec succès !", SnackbarType.Success)
```

---

## 3. Composants visuels de Feedback

Tous ces composants sont dans le module `:designsystem`.

- **`LoadingOverlay`** : Overlay plein écran bloquant les interactions.
- **`AppSnackbar`** : Snackbar personnalisée supportant les types `Success`, `Error`, `Warning`.
- **`EmptyState`** : Composant de remplacement quand une liste est vide.

---

> [!TIP]
> Consultez l'écran de Showcase pour voir ces composants en action et comprendre comment les déclencher.
