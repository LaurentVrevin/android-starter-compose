# Architecture du Projet

Le projet **AndroidStarter** suit les principes de la **Clean Architecture** adaptés au développement Android moderne.

---

## 1. Structure Multi-Modules

### `:core` (Infrastructure Légère)
Module pur Kotlin.
- **`util/`** : Utilitaires génériques.
- **`model/`** : Modèles de données réellement globaux.

### `:data` (Persistance & Réseau)
Implémentation concrète de l'accès aux données.
- **`base/`** : Classes de base (ex: `BaseRepository`).
- **`local/`** : Room Database, DataStore (via `AppPreferences`).
- **`remote/`** : Configuration Ktor ([**`KtorClientFactory.kt`**](../data/src/main/java/com/laurentvrevin/androidstarter/data/remote/KtorClientFactory.kt)).
- **`di/`** : Modules Koin techniques.

### `:designsystem` (Framework UI)
- **`foundation/`** : Jetons de design (Spacing, Shapes, Typography).
- **`components/`** : Composants atomiques réutilisables.
- **`styles/`** : Logique de style centralisée.
- **`ui/`** : Helpers UI (`UiText`, `UiEvent`).

### `:feature:template` (Modèle de Feature)
Feature verticale d'exemple démontrant le flux complet.
- **`presentation/`** : UI et ViewModel.
- **`domain/`** : Modèles et interfaces repository.
- **`data/`** : Implémentation du repository.

### `:app` (Orchestration)
Point d'entrée Android.
- Assemble les modules DI.
- Gère la **Navigation globale** via [`AppNavHost`](../app/src/main/java/com/laurentvrevin/androidstarter/navigation/AppNavHost.kt).

---

## 2. Flux de données (Offline-First)

1.  **UI Layer** : Observe un `Flow` de données.
2.  **Repository** : Gère la synchronisation entre Local (Room) et Remote (Ktor).
3.  **Local Source** : Sert de **Single Source of Truth (SSOT)**.
