# Android Starter Pack 🚀

Bienvenue dans votre **Android Starter Pack**. Ce projet est une base de démarrage (boilerplate) robuste et moderne conçue pour accélérer le développement d'applications Android de qualité professionnelle.

Il intègre les dernières technologies Jetpack Compose, une architecture modulaire et un Design System ultra-flexible.

---

## 🛠 Stack Technique

Le projet utilise les standards les plus récents du développement Android :

- **Kotlin 2.0** : Nouveau compilateur K2 pour des performances accrues.
- **Jetpack Compose (BOM 2026.06.01)** : Développement UI déclaratif moderne.
- **Material 3 (1.4.0)** : Design moderne avec support du Dynamic Color.
- **Koin (4.1.1)** : Injection de dépendances légère et performante, optimisée pour Compose.
- **Room Database** : Persistance de données SQL locale avec support de Coroutines/Flow.
- **DataStore** : Remplacement moderne de SharedPreferences pour les préférences simples.
- **Compose Navigation** : Système de navigation entre écrans avec passage d'arguments.
- **Coroutines & Flow** : Gestion asynchrone et flux de données réactifs.

---

## 🏗 Architecture du Projet

Le projet suit une organisation par couches sémantiques pour favoriser la séparation des responsabilités :

```text
com.laurentvrevin.androidstarter/
├── data/               # Couche Données (Database, DataStore, Repositories)
├── di/                 # Injection de dépendances (Modules Koin)
├── ui/                 # Écrans et logique de features (ViewModels, Screens)
└── designsystem/       # Cœur visuel de l'application (UI Framework)
```

---

## 🎨 Design System

Le cœur visuel de ce starter pack est son **Design System** basé sur les **Theme-based Tokens** et l'**API Styles**. Il est conçu pour être entièrement personnalisable sans modifier la structure des composants.

### Points clés :
- **Tokens Dynamiques** : Accès unifié via `AppTheme.spacing`, `AppTheme.shapes`, etc.
- **Découplage Total** : Apparence visuelle séparée de la logique structurelle via les `StyleProviders`.
- **Showcase Intégré** : Un écran de démonstration interactif est disponible pour visualiser tous les composants et tester le Dark Mode.

> [!IMPORTANT]
> Pour plus de détails sur l'architecture et l'utilisation du Design System, consultez la [**Documentation Spécifique du Design System**](design_system.md).
> Pour la couche réseau et persistance, consultez la [**Documentation Network & Data**](data_network.md).

---

## 🚀 Prise en main (Getting Started)

### 1. Renommage du Package
Pour utiliser ce starter pack sur un nouveau projet, vous devez renommer le package `com.laurentvrevin.androidstarter` par le vôtre dans tous les fichiers (Build scripts, AndroidManifest, et classes Kotlin).

### 2. Configuration de la Base de Données
La stack Room est installée mais aucune entité n'est définie par défaut. Créez vos entités dans `data/local/entities/` et mettez à jour la `AppDatabase`.

### 3. Injection de Dépendances
Les modules Koin sont à définir dans le package `di/` et à charger dans votre classe `Application`.

---

## 📺 Écran de Showcase

Par défaut, l'application se lance sur l'écran `ShowcaseScreen`. Cet écran est votre catalogue de composants. Une fois votre développement commencé, vous pouvez modifier le `setContent` dans votre `MainActivity` pour afficher votre écran principal.

---

*Développé avec passion pour des applications Android performantes.*
