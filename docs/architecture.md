# Architecture du Projet

Le projet **AndroidStarter** suit les principes de la **Clean Architecture** adaptés au développement Android moderne. L'objectif est de garantir une séparation stricte des responsabilités, une testabilité accrue et une grande scalabilité.

---

## 1. Structure des Packages

Le projet est organisé par couches sémantiques :

### `core/` (Infrastructure)
Contient tout ce qui est transversal à l'application et indépendant des données concrètes.
- `base/` : Classes de base (ex: `BaseRepository`).
- `network/` : Types de retour réseau (`NetworkResult`, `NetworkError`).
- `ui/` : Abstractions pour l'interface utilisateur (`UiState`, `UiEvent`, `FeedbackManager`).
- `util/` : Utilitaires génériques (ex: `Mapper`).

### `data/` (Implémentation des Données)
Contient la logique concrète de récupération et de stockage des données.
- `local/` : Persistance (Room Database, DataStore, Entities, DAOs).
- `remote/` : Réseau (Ktor Client, API Services, DTOs).
- `repository/` : Implémentations des Repositories orchestrant le local et le remote.

### `designsystem/` (Framework UI)
Contient le framework visuel complet et les composants atomiques.
- `foundation/` : Jetons de design (Spacing, Shapes, Dimensions).
- `components/` : Composants réutilisables (Buttons, Cards, Feedback).
- `styles/` : Logique de style et providers.
- `theme/` : Point d'accès unique (`AppTheme`).

### `di/` (Injection de Dépendances)
Centralise la configuration de **Koin**. Les modules sont scindés par domaine (`NetworkModule`, `DataModule`, `UiModule`).

---

## 2. Injection de Dépendances (Koin)

Le projet utilise **Koin** pour sa légèreté et son excellente intégration avec Jetpack Compose.
- **Initialisation** : Se fait dans la classe [`App.kt`](../app/src/main/java/com/laurentvrevin/androidstarter/App.kt).
- **Modules** : Chaque couche définit ses propres dépendances via des `module { ... }`.

**Exemple d'injection dans Compose :**
```kotlin
val feedbackManager: FeedbackManager = koinInject()
```

---

## 3. Clean Architecture "Offline-First"

Nous imposons un flux de données strict pour garantir une expérience utilisateur fluide :

1.  **UI Layer** : Observe un `Flow` de données provenant uniquement du Repository.
2.  **Repository Layer** : Sert de pont. Il récupère les données du réseau, les enregistre dans la base locale (SSOT) et laisse le Flow réactif mettre à jour l'UI.
3.  **Data Layer** : Fournit les implémentations Ktor et Room.

> [!TIP]
> Toujours utiliser le pattern **Single Source of Truth (SSOT)** : L'UI n'observe JAMAIS directement le réseau, seulement la base de données locale.
