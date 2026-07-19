# Android Starter Pack 🚀

Bienvenue dans votre **Android Starter Pack**. Ce projet est une base de démarrage (boilerplate) robuste et moderne conçue pour accélérer le développement d'applications Android de qualité professionnelle.

---

## 🛠 Stack Technique

- **Kotlin 2.0** : Nouveau compilateur K2.
- **Jetpack Compose (BOM)** : Développement UI déclaratif.
- **Material 3** : Design system moderne avec support du Dynamic Color.
- **Koin** : Injection de dépendances légère.
- **Ktor Client** : Réseau résilient et configurable.
- **Room & DataStore** : Persistance locale (Offline-First).
- **Coroutines & Flow** : Gestion asynchrone réactive.

---

## 🏗 Architecture

Le projet suit une architecture **multi-modules** propre et évolutive :

```text
AndroidStarter/
├── :app                # Orchestration, Navigation globale, Configuration (BuildConfig)
├── :core               # Infrastructure légère, utilitaires transversaux
├── :data               # Couche données (Ktor, Room, DataStore)
├── :designsystem       # UI Framework complet (Tokens, Styles, Composants, Themes)
├── :feature:template   # Feature d'exemple pédagogique (à copier ou supprimer)
└── build-logic/        # Convention Plugins Gradle
```

---

## ✨ Personnaliser le Design System

Le design system est centralisé dans le module `:designsystem`. Pour le rebrander :

1.  **Couleurs** : Modifiez les palettes primitives dans `foundation/AppColorsScheme.kt`.
2.  **Typographie** : Ajustez les polices et échelles dans `foundation/AppTypography.kt`.
3.  **Thème** : Vérifiez l'assemblage clair/sombre dans `theme/AppTheme.kt`.
4.  **Styles** : Ajustez les variantes globales (Boutons, Cartes, Champs) dans le package `styles/`.
5.  **Visualisation** : Utilisez le **Showcase** intégré pour valider vos changements instantanément.

---

## 🚀 Créer une nouvelle application depuis le starter

1.  **Dupliquer** le repository.
2.  **Renommer** le projet via les scripts :
    - Windows : `./scripts/rename_project.ps1 -NewPackageName "com.votre.app" -NewAppName "MonApp"`
    - Bash : `./scripts/rename_project.sh "com.votre.app" "MonApp"`
3.  **Modifier** le nom visible dans `res/values/strings.xml`.
4.  **Supprimer** la feature Template une fois les bases comprises (voir guide dans `docs/`).
5.  **Lancer** `./gradlew assembleDebug` pour valider.

---

## 📚 Documentation Technique

1.  📂 [**Architecture & Organisation**](docs/architecture.md)
2.  🎨 [**Design System & UI**](docs/design_system.md)
3.  🌐 [**Réseau (Ktor)**](docs/network.md)
4.  💾 [**Données (Room & DataStore)**](docs/data.md)
5.  🧪 [**Stratégie de Test**](docs/testing.md)
6.  🔄 [**Guide de Renommage**](docs/renaming.md)

---

*Développé avec passion pour des applications Android performantes.*
