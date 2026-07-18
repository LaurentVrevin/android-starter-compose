# Scalabilité & Modularisation

Le projet **AndroidStarter** est structuré de manière multi-modules pour garantir une maintenance aisée et des temps de compilation optimisés.

---

## 1. Structure Multi-Modules

### `:core` (Infrastructure)
Le module de base sans dépendances Android lourdes.
- **Responsabilité** : Types de base, `UiState`, `NetworkResult`, utilitaires de mapping.
- **Dépendances** : Kotlin, Coroutines, Serialization.

### `:designsystem` (UI Framework)
Le module contenant tout l'aspect visuel.
- **Responsabilité** : Tokens, Styles, Composants atomiques, Thème.
- **Dépendances** : `:core`, Compose, Material 3.

### `:data` (Persistance & Réseau)
Le module gérant l'accès aux données.
- **Responsabilité** : Room, DataStore, Ktor, Repositories.
- **Dépendances** : `:core`, Room, Ktor, Koin.

### `:app` (Orchestration)
Le point d'entrée de l'application.
- **Responsabilité** : `MainActivity`, `Application`, Navigation globale, Injection de dépendances finale.
- **Dépendances** : `:core`, `:designsystem`, `:data`.

---

## 2. Convention Plugins (build-logic)

Pour éviter la duplication de configuration dans les fichiers `build.gradle.kts`, nous utilisons des **Convention Plugins** situés dans le dossier `build-logic`.

### Plugins disponibles :
- `androidstarter.android.application` : Pour les modules d'application.
- `androidstarter.android.library` : Pour les modules de bibliothèque Android.
- `androidstarter.android.library.compose` : Pour les bibliothèques utilisant Jetpack Compose.
- `androidstarter.android.application.compose` : Pour l'application utilisant Jetpack Compose.

### Comment créer un nouveau module :
1. Créez un nouveau dossier de module (ex: `:feature:home`).
2. Ajoutez-le dans `settings.gradle.kts`.
3. Dans son `build.gradle.kts`, appliquez le plugin souhaité :
```kotlin
plugins {
    id("androidstarter.android.library.compose")
}
```

---

## 3. Bénéfices
- **Compilation incrémentale** : Gradle ne recompile que les modules modifiés.
- **Isolation** : Impossible d'accéder à la base de données (`:data`) depuis le `:designsystem` par erreur.
- **Réutilisabilité** : Le module `:designsystem` peut être partagé entre plusieurs applications.
