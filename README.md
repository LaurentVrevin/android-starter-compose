# Android Starter Pack 🚀

Bienvenue dans votre **Android Starter Pack**. Ce projet est une base de démarrage (boilerplate) robuste et moderne conçue pour accélérer le développement d'applications Android de qualité professionnelle.

Il intègre les dernières technologies Jetpack Compose, une architecture modulaire et un Design System ultra-flexible.

---

## 🛠 Stack Technique

Le projet utilise les standards les plus récents du développement Android :

- **Kotlin 2.0** : Nouveau compilateur K2.
- **Jetpack Compose (BOM 2026.06.01)** : Développement UI déclaratif.
- **Material 3 (1.4.0)** : Support du Dynamic Color.
- **Koin (4.1.1)** : Injection de dépendances légère.
- **Ktor Client** : Réseau résilient et performant.
- **Room & DataStore** : Persistance locale (Offline-First).
- **Coroutines & Flow** : Gestion asynchrone réactive.

---

## 🏗 Architecture du Projet

Le projet suit une architecture **multi-modules** pour favoriser la scalabilité et l'isolation des responsabilités :

```text
AndroidStarter/
├── :app                # Application Android (MainActivity, DI global)
├── :core               # Infrastructure transversale (Network types, UiState, Utils)
├── :data               # Implémentation des données (Room, DataStore, Ktor, Repositories)
├── :designsystem       # Framework UI complet (Tokens, Styles, Composants)
└── build-logic/        # Convention Plugins pour la configuration Gradle unifiée
```

---

## 📚 Documentation Technique

Pour bien prendre en main le starter pack, consultez les guides détaillés par thématique :

1.  📂 [**Architecture & Organisation**](docs/architecture.md) : Clean Architecture, packages et DI (Koin).
2.  🎨 [**Design System & UI**](docs/design_system.md) : Tokens, Styles API et composants de feedback.
3.  🌐 [**Réseau (Ktor)**](docs/network.md) : Pattern Result, gestion d'erreurs et mocking.
4.  💾 [**Données (Room & DataStore)**](docs/data.md) : Persistance, SSOT et mappers.
5.  🚦 [**UI State & Feedback**](docs/ui_state.md) : Gestion des états et événements éphémères.
6.  🧪 [**Stratégie de Test**](docs/testing.md) : Tests unitaires Network et instrumentation Database.

---

## 🚀 Prise en main rapide

1.  **Renommer le Package** : Remplacez `com.laurentvrevin.androidstarter` par votre identifiant unique.
2.  **Showcase** : L'application se lance sur l'écran `ShowcaseScreen` pour vous permettre de visualiser tous les composants disponibles.
3.  **Vérification** : Lancez `./gradlew test` et `./gradlew connectedAndroidTest` pour valider l'infrastructure technique.

---

## 📺 Aperçu Interactif

L'écran de **Showcase** intégré permet de tester :
- Le basculement Light/Dark mode.
- L'échelle de spacing et shapes.
- Tous les composants (Buttons, Cards, Chips, Inputs).
- La simulation de synchronisation de données.

---

*Développé avec passion pour des applications Android performantes.*
