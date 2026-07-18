# Documentation du Design System (AndroidStarter)

Bienvenue dans la documentation technique du Design System de l'application **AndroidStarter**. Ce document sert de guide de référence pour comprendre l'architecture, utiliser les composants et étendre le système.

## 1. Philosophie & Objectifs
L'objectif de ce Design System est de fournir une base **cohérente**, **flexible** et **performante** pour le développement d'interfaces Android modernes avec Jetpack Compose.

*   **Cohérence** : Utilisation stricte de jetons (tokens) pour les couleurs, typographies et espacements.
*   **Flexibilité** : Architecture découplée permettant de changer tout l'aspect visuel sans toucher au code des composants.
*   **Rapidité** : Composants pré-configurés pour les cas d'usage les plus courants.

---

## 2. Architecture Technique

Le système repose sur deux piliers majeurs :

### A. Theme-based Tokens
Toutes les valeurs fondamentales sont encapsulées dans des `data class` immuables, injectées via des `CompositionLocal`.
L'accès se fait via un objet proxy unique : [**`AppTheme`**](file:///D:/AndroidProjects/AndroidStarter/app/src/main/java/com/laurentvrevin/androidstarter/designsystem/theme/AppDesignSystem.kt).

**Exemple d'utilisation :**
```kotlin
Modifier.padding(AppTheme.spacing.medium)
Modifier.clip(AppTheme.shapes.large)
```

### B. Style Providers & API Styles
Les composants (boutons, cards, etc.) ne contiennent aucune définition de couleur ou de padding statique. Ils délèguent leur apparence à des **Style Providers**. Cela permet d'injecter des styles différents selon le contexte (ex: thème spécifique par marque).

---

## 3. Les Jetons de Fondation (Tokens)

### Spacing ([AppSpacing.kt](file:///D:/AndroidProjects/AndroidStarter/app/src/main/java/com/laurentvrevin/androidstarter/designsystem/foundation/AppSpacing.kt))
Échelle d'espacement standardisée pour les marges et les paddings.
- `extraSmall` (4.dp)
- `small` (8.dp)
- `medium` (12.dp)
- `standard` (16.dp)
- `large` (20.dp)
- `extraLarge` (24.dp)
- `doubleLarge` (32.dp)
- `tripleLarge` (48.dp)

### Shapes ([AppShapes.kt](file:///D:/AndroidProjects/AndroidStarter/app/src/main/java/com/laurentvrevin/androidstarter/designsystem/foundation/AppShapes.kt))
- `small` (8.dp)
- `medium` (12.dp)
- `large` (16.dp)
- `extraLarge` (24.dp)
- `pill` (999.dp)

### Dimensions ([AppDimensions.kt](file:///D:/AndroidProjects/AndroidStarter/app/src/main/java/com/laurentvrevin/androidstarter/designsystem/foundation/AppDimensions.kt))
Centralise les hauteurs cibles des composants interactifs.
- `button.heightMedium` (48.dp)
- `chip.heightSmall` (32.dp)
- `icon.sizeMedium` (24.dp)

---

## 4. Typographie & Couleurs

### Typographie ([AppTypography.kt](file:///D:/AndroidProjects/AndroidStarter/app/src/main/java/com/laurentvrevin/androidstarter/designsystem/theme/AppTypography.kt))
Utilise l'échelle Material 3 harmonisée :
- `display`, `h1`, `h2`
- `titleLarge`
- `bodyLarge`, `bodySmall`
- `labelMedium`

### Couleurs ([AppColorsScheme.kt](file:///D:/AndroidProjects/AndroidStarter/app/src/main/java/com/laurentvrevin/androidstarter/designsystem/theme/AppColorsScheme.kt))
Le système gère nativement le **Mode Clair** et le **Mode Sombre** via `AppTheme`. Les couleurs sont sémantiques (ex: `primary`, `surface`, `error`).

---

## 5. Catalogue de Composants

Tous les composants se trouvent dans le package [`designsystem/components`](file:///D:/AndroidProjects/AndroidStarter/app/src/main/java/com/laurentvrevin/androidstarter/designsystem/components).

### Boutons (`AppButton`)
- **Variantes** : `AppPrimaryButton`, `AppSecondaryButton`, `AppOutlinedButton`, `AppGhostButton`, `AppDangerButton`.
- **Tailles** : Supporte `AppSize.Small`, `Medium`, `Large`.
- **États** : Gère automatiquement l'état `pressed` avec animation.

### Cards (`AppCard`)
- **Styles** : `default()`, `elevated()`, `outlined()`.
- Gère automatiquement l'élévation et les bordures via l'API Styles.

---

## 6. Patterns de Mise en Page

Situés dans le package [`designsystem/patterns`](file:///D:/AndroidProjects/AndroidStarter/app/src/main/java/com/laurentvrevin/androidstarter/designsystem/patterns).

*   **`ScreenContainer`** : À utiliser comme racine de chaque écran. Assure le fond correct et le padding de sécurité.
*   **`SectionBlock`** : Structure le contenu avec un titre et un espacement vertical cohérent.

---

## 7. Comment étendre le système ?

### Ajouter un nouveau Jeton
1. Créer/Modifier la `data class` dans `foundation/`.
2. Mettre à jour `AppTheme` dans `AppDesignSystem.kt` pour l'exposer.
3. Fournir la valeur par défaut dans `AppTheme.kt`.

### Créer un nouveau Composant
1. Créer le fichier dans `components/`.
2. Définir une interface de style dans `styles/`.
3. Utiliser `Modifier.styleable` pour appliquer le style au composant.
4. Injecter l'implémentation par défaut dans `AppTheme.kt`.

---

> [!TIP]
> Utilisez l'écran de **[Showcase](file:///D:/AndroidProjects/AndroidStarter/app/src/main/java/com/laurentvrevin/androidstarter/designsystem/ShowcaseScreen.kt)** pour tester vos modifications visuellement en temps réel.
