# Architecture du Projet

Le projet **AndroidStarter** suit les principes de la **Clean Architecture** adaptés au développement Android moderne. L'objectif est de garantir une séparation stricte des responsabilités, une testabilité accrue et une grande scalabilité.

---

## 1. Structure Multi-Modules

Le projet est éclaté en modules Gradle isolés pour favoriser la compilation incrémentale et l'isolation des couches :

### `:core` (Infrastructure)
Module pur Kotlin léger.
- **`util/`** : Utilitaires génériques (ex: `Mapper`).
- **`model/`** : Modèles de données partagés entre les modules.

### `:data` (Persistance & Réseau)
Implémentation concrète de l'accès aux données.
- **`base/`** : Classes de base pour les repositories.
- **`network/`** : Types de retour et erreurs réseau.
- **`local/`** : Room Database, DataStore (via `AppPreferences`), Entities, DAOs.
- **`remote/`** : Configuration Ktor ([**`KtorClientFactory.kt`**](../data/src/main/java/com/laurentvrevin/androidstarter/data/remote/KtorClientFactory.kt)).
- **`di/`** : Modules Koin `:data`.

### `:designsystem` (Framework UI)
- **`foundation/`** : Jetons de design (Spacing, Shapes, Dimensions).
- **`components/`** : Composants atomiques réutilisables.
- **`ui/`** : Helpers UI (`UiState`, `UiEvent`, `FeedbackManager`, `UiText`).
- **`theme/`** : Point d'accès unique.

### `:feature:sample` (Démonstration)
Feature verticale d'exemple démontrant le flux complet.
- Contient son propre `ViewModel`, `Screen`, `UiState` et son module Koin.

### `:app` (Orchestration)
Le point d'entrée Android.
- Assemble les modules DI.
- Gère la **Navigation globale** via [`AppNavHost`](../app/src/main/java/com/laurentvrevin/androidstarter/navigation/AppNavHost.kt).

---

## 2. Clean Architecture "Offline-First"

Nous imposons un flux de données strict pour garantir une expérience utilisateur fluide :

1.  **UI Layer** : Observe un `Flow` de données provenant uniquement du Repository.
2.  **Repository Layer** : Sert de pont. Il récupère les données du réseau, les enregistre dans la base locale (SSOT) et laisse le Flow réactif mettre à jour l'UI.
3.  **Data Layer** : Fournit les implémentations Ktor et Room.

> [!TIP]
> Toujours utiliser le pattern **Single Source of Truth (SSOT)** : L'UI n'observe JAMAIS directement le réseau, seulement la base de données locale.
