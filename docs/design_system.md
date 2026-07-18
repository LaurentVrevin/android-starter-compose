# Documentation du Design System

Le Design System d'AndroidStarter est basé sur une architecture de **Tokens** et l'**API Styles** de Jetpack Compose. Il permet une personnalisation totale sans modifier la structure des composants.

---

## 1. Accès aux Tokens

L'objet [**`AppTheme`**](../app/src/main/java/com/laurentvrevin/androidstarter/designsystem/theme/AppDesignSystem.kt) est le point d'entrée unique. Toutes les valeurs sont injectées dynamiquement.

```kotlin
// Spacing
Modifier.padding(AppTheme.spacing.standard)

// Shapes
Modifier.clip(AppTheme.shapes.medium)

// Typography
Text(text = "Hello", style = AppTheme.typography.h1)
```

### Échelles disponibles
- **Spacing** : `extraSmall` (4dp) à `tripleLarge` (48dp).
- **Shapes** : `small`, `medium`, `large`, `extraLarge`, `pill`.
- **Dimensions** : Hauteurs cibles pour `button`, `chip` et `icon`.

---

## 2. API Styles & Style Providers

Chaque composant atomique est découplé de son apparence visuelle.

### Fonctionnement
Un composant comme `AppButton` demande son style au fournisseur local :
```kotlin
val buttonStyle = style ?: LocalButtonStyles.current.primary(size)
```

### Personnalisation
Pour changer l'apparence de TOUS les boutons de l'application, il suffit de modifier l'implémentation dans [`DefaultButtonStyles.kt`](../app/src/main/java/com/laurentvrevin/androidstarter/designsystem/styles/ButtonStyles.kt).

---

## 3. Composants de Feedback

### Snackbars (`AppSnackbar`)
Gérées via le `FeedbackManager`. Supporte :
- `Default`, `Success`, `Error`, `Warning`.

### Loading Overlay (`LoadingOverlay`)
Bloque l'interface pendant un chargement.
```kotlin
LoadingOverlay(isLoading = state.isLoading)
```

### Empty State (`EmptyState`)
Affiche un message et une action optionnelle quand une liste est vide.

---

## 4. Visualisation (Showcase)

L'écran [`ShowcaseScreen.kt`](../app/src/main/java/com/laurentvrevin/androidstarter/designsystem/ShowcaseScreen.kt) permet de tester interactivement :
- Le switch **Dark/Light Mode**.
- Toutes les variantes de boutons et tailles.
- Les échelles visuelles de spacing et shapes.

> [!IMPORTANT]
> Avant d'utiliser un nouveau composant, vérifiez son rendu dans le Showcase en activant le mode sombre pour valider les contrastes.
