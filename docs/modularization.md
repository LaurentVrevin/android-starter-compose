# Scalabilité & Modularisation

Le projet **AndroidStarter** est structuré de manière multi-modules pour garantir une maintenance aisée et des performances de compilation optimales.

---

## 1. Structure des Modules

### `:core` (Infrastructure Légère)
Types transversaux purs Kotlin.
- **Responsabilité** : Modèles partagés (`:core:model`), utilitaires génériques.
- **Dépendances** : Minimales (Kotlin, Serialization).

### `:designsystem` (UI Framework)
Le système de design isolé.
- **Responsabilité** : Tokens, Thèmes, Composants atomiques réutilisables, Helpers UI (`UiText`, `UiEvent`).
- **Dépendances** : Compose, Material 3.

### `:data` (Couche Données)
L'implémentation de la persistance et du réseau.
- **Responsabilité** : Room, DataStore, Ktor, Repositories concrets.
- **Dépendances** : `:core`, Room, Ktor.

### `:feature:sample` (Exemple)
Feature verticale complète servant de modèle.
- **Responsabilité** : Logique métier et UI d'un écran spécifique.
- **Dépendances** : `:core`, `:designsystem`, `:data`.

### `:app` (Orchestration Finale)
Point d'entrée Android.
- **Responsabilité** : `MainActivity`, Navigation globale, Assemblage de l'injection de dépendances.
- **Dépendances** : Tous les modules de feature.

---

## 2. Convention Plugins (build-logic)

Nous centralisons la configuration Gradle dans `build-logic` pour éviter la duplication.

### Plugins disponibles :
- `androidstarter.android.application`
- `androidstarter.android.library`
- `androidstarter.android.library.compose`
- `androidstarter.android.application.compose`

---

## 3. Bénéfices
- **Compilation incrémentale** : Seuls les modules impactés sont recompilés.
- **Frontières claires** : Impossible d'appeler accidentellement des composants internes d'autres couches.
- **Tests isolés** : Chaque module possède sa propre suite de tests dédiée.
