# UI State & Feedback

---

## 1. Gestion de l'état (UiState)

Chaque écran doit être piloté par une data class spécifique (ex: `TemplateUiState`).

```kotlin
@Immutable
data class TemplateUiState(
    val items: List<TemplateItem> = emptyList(),
    val isInitialLoading: Boolean = true,
    val error: UiText? = null
)
```

---

## 2. Événements One-shot

Utilisez [`UiText`](../designsystem/src/main/java/com/laurentvrevin/androidstarter/designsystem/ui/UiText.kt) pour l'affichage des messages localisés sans Context.
