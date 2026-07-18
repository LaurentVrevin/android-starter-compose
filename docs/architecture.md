# Architecture du Projet

Le projet **AndroidStarter** suit les principes de la **Clean Architecture** adaptés au développement Android moderne. L'objectif est de garantir une séparation stricte des responsabilités, une testabilité accrue et une grande scalabilité.

---

## 1. Structure Multi-Modules

Le projet est éclaté en modules Gradle isolés pour favoriser la compilation incrémentale et l'isolation des couches :

### `:core` (Infrastructure)
Module pur Kotlin/Compose sans logique métier.
- **`base/`** : Classes de base (ex: `BaseRepository`).
- **`network/`** : Types de retour réseau (`NetworkResult`, `NetworkError`).
- **`ui/`** : Abstractions pour l'interface utilisateur (`UiState`, `UiEvent`, `FeedbackManager`).
- **`util/`** : Utilitaires génériques (ex: `Mapper`).
- **`di/`** : Module Koin `:core` ([**`UiModule.kt`**](../core/src/main/java/com/laurentvrevin/androidstarter/core/di/UiModule.kt)).

### `:data` (Persistance & Réseau)
Implémentation concrète de l'accès aux données.
- **`local/`** : Room Database, DataStore, Entities, DAOs.
- **`remote/`** : Configuration Ktor ([**`KtorClientFactory.kt`**](../data/src/main/java/com/laurentvrevin/androidstarter/data/remote/KtorClientFactory.kt)), API Services.
- **`di/`** : Modules Koin `:data` ([**`DataModule.kt`**](../data/src/main/java/com/laurentvrevin/androidstarter/data/di/DataModule.kt), [**`NetworkModule.kt`**](../data/src/main/java/com/laurentvrevin/androidstarter/data/di/NetworkModule.kt)).

### `:designsystem` (Framework UI)
Le cerveau visuel de l'application.
- **`foundation/`** : Jetons de design (Spacing, Shapes, Dimensions).
- **`components/`** : Composants atomiques réutilisables.
- **`styles/`** : Logique de style et providers.
- **`theme/`** : Point d'accès unique ([**`AppTheme`**](../designsystem/src/main/java/com/laurentvrevin/androidstarter/designsystem/theme/AppDesignSystem.kt)).

### `:app` (Orchestration)
Le point d'entrée Android.
- Contient la `MainActivity` et la classe [`App.kt`](../app/src/main/java/com/laurentvrevin/androidstarter/App.kt) qui assemble tous les modules Koin.

---

## 2. Clean Architecture "Offline-First"

Nous imposons un flux de données strict pour garantir une expérience utilisateur fluide :

1.  **UI Layer** : Observe un `Flow` de données provenant uniquement du Repository.
2.  **Repository Layer** : Sert de pont. Il récupère les données du réseau, les enregistre dans la base locale (SSOT) et laisse le Flow réactif mettre à jour l'UI.
3.  **Data Layer** : Fournit les implémentations Ktor et Room.

> [!TIP]
> Toujours utiliser le pattern **Single Source of Truth (SSOT)** : L'UI n'observe JAMAIS directement le réseau, seulement la base de données locale.
