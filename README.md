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
└── build-logic/        # Convention Plugins Gradle & Bootstrap task
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

## 🚀 Créer un nouveau projet depuis le template

Ce dépôt est un **Template GitHub**. Suivez ces étapes pour initialiser votre propre application en quelques minutes.

### 1. Utiliser le Template
Sur GitHub, cliquez sur le bouton vert **"Use this template"** > **"Create a new repository"**. Donnez-lui le nom de votre choix (ex: `MonAppRepo`).

### 2. Cloner le projet
Clonez votre nouveau dépôt sur votre machine :
```bash
git clone https://github.com/votre-user/MonAppRepo.git
cd MonAppRepo
```

### 3. Lancer le Bootstrap
Initialisez l'identité de votre projet avec la tâche Gradle interactive :

**Windows :**
```powershell
.\gradlew.bat bootstrapProject
```

**macOS / Linux :**
```bash
./gradlew bootstrapProject
```

Le script vous demandera :
- **Gradle Project Name** : Le nom technique du projet (PascalCase recommandé, ex: `Wheris`).
- **Application Display Name** : Le nom visible de l'application (ex: `Wheris`).
- **Android Package Name** : L'identifiant unique (ex: `com.laurentvrevin.wheris`). *Minimum 2 segments, sans tirets.*

### 4. Confirmer les changements
Après avoir saisi les informations, un résumé s'affichera. Tapez `y` pour confirmer. 
Le script va alors :
- Renommer les namespaces, applicationId et packages.
- Mettre à jour les imports et les ressources (thèmes, strings).
- **Déplacer physiquement les dossiers** de code pour correspondre au nouveau package.
- Créer un fichier `.bootstrap-complete` pour empêcher toute réexécution accidentelle.

### 5. Synchroniser et Développer
- Ouvrez le projet dans **Android Studio**.
- Cliquez sur **"Sync Project with Gradle Files"**.
- Lancez l'application !

> [!TIP]
> Pour automatiser le bootstrap sans prompt (ex: dans une CI), utilisez :
> `.\gradlew.bat bootstrapProject -PprojectName=App -PappDisplayName="My App" -PpackageName=com.me.app -Pconfirm=true`

---

## 🛡 Sécurité et Intégrité

- **Validation** : Le script vérifie la validité du package name avant toute modification.
- **Dry Run** : Ajoutez `-PdryRun=true` pour voir les changements prévus sans modifier aucun fichier.
- **Réinitialisation** : Pour repartir du template propre, supprimez le fichier `.bootstrap-complete` et restaurez `starter.properties` (ou reclonnez).

---

## 📚 Documentation Technique

1.  📂 [**Architecture & Organisation**](docs/architecture.md)
2.  🎨 [**Design System & UI**](docs/design_system.md)
3.  🌐 [**Réseau (Ktor)**](docs/network.md)
4.  💾 [**Données (Room & DataStore)**](docs/data.md)
5.  🧪 [**Stratégie de Test**](docs/testing.md)

---

*Développé avec passion pour des applications Android performantes.*
