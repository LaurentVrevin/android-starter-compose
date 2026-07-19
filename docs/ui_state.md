# UI State & Feedback

La gestion des états de l'interface utilisateur est cruciale pour une application réactive et robuste.

---

## 1. Gestion de l'état (UiState)

Chaque écran doit être piloté par une data class spécifique à la feature (par exemple `SampleUiState`), contenant des propriétés immutables.

```kotlin
@Immutable
data class SampleUiState(
    val items: List<SampleItem> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: UiText? = null
)
```

### Pattern recommandé

1.  **ViewModel** : Gère la logique et expose un `StateFlow<SampleUiState>`.
2.  **Screen** : Consomme l'état avec `collectAsStateWithLifecycle()`.

```kotlin
val state by viewModel.state.collectAsStateWithLifecycle()
```

---

## 2. Événements "One-shot" (`UiEvent`)

Pour les actions éphémères (Snackbar, Navigation), on utilise soit :
- Des propriétés dans le State (ex: `val message: UiText?`) remises à null après affichage.
- Le `FeedbackManager` pour des événements réellement globaux.

### UiText
Utilisez [`UiText`](../designsystem/src/main/java/com/laurentvrevin/androidstarter/designsystem/ui/UiText.kt) pour passer des chaînes de caractères (ressources ou brutes) de la couche logique à l'UI sans dépendre du Context Android.

---

## 3. Feedback visuel

Le Design System fournit des composants prêts à l'emploi :
- `LoadingOverlay` : Pour les chargements bloquants.
- `EmptyState` : Pour les listes vides.
- `AppSnackbar` : Pour les notifications rapides.
