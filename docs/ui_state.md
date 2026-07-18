# UI State & Feedback Management

Cette section explique comment gérer les états de vos écrans et les interactions avec l'utilisateur de manière réactive et centralisée.

---

## 1. Gestion des États (`UiState`)

Chaque écran doit être piloté par une [`UiState`](../app/src/main/java/com/laurentvrevin/androidstarter/core/ui/UiState.kt).

```kotlin
sealed interface UiState<out T> {
    data object Idle : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}
```

### Dans le ViewModel
```kotlin
private val _state = MutableStateFlow<UiState<User>>(UiState.Idle)
val state = _state.asStateFlow()

fun loadUser() {
    _state.value = UiState.Loading
    // ... appel repository ...
    _state.value = UiState.Success(user)
}
```

---

## 2. Événements "One-shot" (`UiEvent`)

Pour les actions qui ne sont pas des états (ex: afficher une Snackbar, naviguer), on utilise [`UiEvent`](../app/src/main/java/com/laurentvrevin/androidstarter/core/ui/UiEvent.kt).

Ces événements sont diffusés via le **`FeedbackManager`**.

### Envoyer un événement
```kotlin
feedbackManager.showSnackbar("Enregistré avec succès !", SnackbarType.Success)
```

### Observer les événements (Root de l'app)
Le `FeedbackManager` expose un `SharedFlow` que vous devez collecter dans votre activité principale ou votre composant de navigation racine pour afficher les Snackbars.

---

## 3. Composants visuels

### `LoadingOverlay`
À utiliser pour bloquer l'interaction pendant un chargement critique.
```kotlin
if (state is UiState.Loading) {
    LoadingOverlay()
}
```

### `AppSnackbar`
Composant stylisé selon le Design System, à injecter dans votre `Scaffold`.

---

> [!TIP]
> Pour une expérience utilisateur optimale, privilégiez les "shimmer" ou loaders locaux au sein des composants plutôt que le `LoadingOverlay` plein écran quand c'est possible.
