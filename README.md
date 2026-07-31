# Android Starter Pack 🚀

Bienvenue dans ton **Android Starter Pack**. Ce projet est une base de démarrage (boilerplate) robuste et moderne conçue pour accélérer le développement d'applications Android de qualité professionnelle.

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

1.  **Couleurs** : Modifie les palettes primitives dans `foundation/AppColorsScheme.kt`.
2.  **Typographie** : Ajuste les polices et échelles dans `foundation/AppTypography.kt`.
3.  **Thème** : Vérifie l'assemblage clair/sombre dans `theme/AppTheme.kt`.
4.  **Styles** : Ajuste les variantes globales (Boutons, Cartes, Champs) dans le package `styles/`.
5.  **Visualisation** : Utilise le **Showcase** intégré pour valider tes changements instantanément.

---

## Créer un nouveau projet depuis le template

Ce dépôt est un **Template GitHub**. Suis ces étapes pour initialiser ta propre application en quelques minutes.

### Étape 1 — Créer le dépôt depuis GitHub

1. Ouvre le dépôt [android-starter-compose](https://github.com/LaurentVrevin/android-starter-compose) sur GitHub.
2. Clique sur le bouton vert **"Use this template"**.
3. Choisis **"Create a new repository"**.
4. Donne un nom à ton nouveau dépôt (ex: `mon-application-android`).
5. Choisis sa visibilité (Public ou Private).
6. Clique sur **"Create repository from template"**.

> [!NOTE]
> Le nom du dépôt GitHub peut être différent du nom Gradle. Par exemple, ton dépôt peut s'appeler `mon-application-android` alors que ton projet s'appellera `MonApplication`.

> [!IMPORTANT]
> **Ne jamais exécuter le bootstrap directement dans le dépôt original.**
> La tâche doit uniquement être exécutée dans un nouveau dépôt créé avec "Use this template".

### Étape 2 — Cloner dans Android Studio

Tu peux cloner le projet de deux manières :

**Méthode Android Studio :**
1. Ouvre Android Studio.
2. Choisis **"Get from VCS"**.
3. Sélectionne **Git**.
4. Colle l'URL de ton nouveau dépôt.
5. Choisis le dossier local de destination.
6. Clique sur **Clone**.
7. Accepte **"Trust Project"** si demandé.
8. Attends la fin de la synchronisation Gradle initiale.

**Méthode Terminal :**
```bash
git clone https://github.com/ton-user/mon-application-android.git
cd mon-application-android
```

Toutes les commandes suivantes doivent être lancées depuis la racine du projet (là où se trouvent `gradlew` et `settings.gradle.kts`).

### Étape 3 — Vérifier le projet avant initialisation

Vérifie que tu es sur une base propre et que la tâche de bootstrap est bien disponible.

**Windows PowerShell :**
```powershell
git status
.\gradlew.bat tasks --all | Select-String "bootstrapProject"
```

**macOS / Linux :**
```bash
git status
./gradlew tasks --all | grep bootstrapProject
```

`bootstrapProject` doit apparaître dans la liste des tâches disponibles.

### Étape 4 — Effectuer un dry-run

Avant d'appliquer les changements, effectue une simulation pour vérifier ce qui sera modifié.

**Windows PowerShell :**
```powershell
.\gradlew.bat bootstrapProject `
  -PprojectName=MonApplication `
  -PappDisplayName="Mon application" `
  -PpackageName=com.exemple.monapplication `
  -PdryRun=true
```

**macOS / Linux :**
```bash
./gradlew bootstrapProject \
  -PprojectName=MonApplication \
  -PappDisplayName="Mon application" \
  -PpackageName=com.exemple.monapplication \
  -PdryRun=true
```

Le mode `dryRun=true` ne modifie aucun fichier. Il affiche la liste des fichiers qui seraient mis à jour et les dossiers qui seraient déplacés. Ton `git status` doit rester inchangé après ce test.

### Étape 5 — Lancer le bootstrap interactif

Une fois prêt, lance la commande simplifiée. Le script te guidera pas à pas.

**Windows :**
```powershell
.\gradlew.bat bootstrapProject
```

**macOS / Linux :**
```bash
./gradlew bootstrapProject
```

**Exemple d'interaction :**
```text
Enter Gradle Project Name (PascalCase, e.g. Wheris): MonApplication
Enter Application Display Name (e.g. Wheris App): Mon application
Enter Android Package Name (e.g. com.my.app): com.exemple.monapplication

🚀 Bootstrapping project...
   ...
⚠️  Confirm applying these changes? (y/N): y
```

**Différence entre les noms :**
- **Gradle Project Name** : Nom technique du projet (ex: `MonApplication`). Sans espace.
- **Application Display Name** : Nom visible sous l'icône de l'application (ex: `Mon application`). Peut contenir des espaces.
- **Android Package Name** : Identifiant unique de l'application (ex: `com.exemple.monapplication`).

### Étape 6 — Règles des valeurs

Le script valide strictement tes saisies :

**Pour `projectName` :**
- PascalCase obligatoire (ex: `MonApplication`).
- Doit commencer par une majuscule.
- Aucun espace, tiret, point ou caractère spécial.

**Pour `packageName` :**
- Au moins deux segments (ex: `com.monapp`).
- Caractères autorisés : lettres minuscules, chiffres et underscores.
- Aucun tiret.
- Ne doit pas commencer ou finir par un point.
- Ne doit pas être `com.example`.
- Doit être différent du package source du starter.

### Étape 7 — Transformations effectuées

La tâche `bootstrapProject` automatise les actions suivantes :
- Mise à jour de `rootProject.name` dans `settings.gradle.kts`.
- Mise à jour de `app_name` dans les ressources.
- Mise à jour de l'`applicationId` et des `namespace` Gradle.
- Mise à jour de toutes les déclarations `package` et des `import` dans le code Kotlin/Java.
- Renommage du thème Android (`Theme.AndroidStarter` -> `Theme.MonApplication`).
- **Déplacement physique des dossiers** sur le disque pour correspondre à la nouvelle structure de package.
- Traitement de tous les modules (`:app`, `:core`, `:data`, etc.) et de tous les environnements (`main`, `test`, `androidTest`).

Les plugins de convention génériques (`com.laurentvrevin.android.*`) restent inchangés.

### Étape 8 — Synchroniser Android Studio

Une fois le bootstrap terminé avec succès :
1. Dans Android Studio, fais **File > Sync Project with Gradle Files**.
2. Il est recommandé de fermer puis rouvrir le projet si l'ancien nom persiste dans l'interface de l'IDE.

### Étape 9 — Compiler le projet généré

Vérifie que tout fonctionne en lançant une compilation complète.

**Windows :**
```powershell
.\gradlew.bat clean :app:assembleDebug
```

**macOS / Linux :**
```bash
./gradlew clean :app:assembleDebug
```

Tu dois obtenir un **BUILD SUCCESSFUL**.

### Étape 10 — Vérifier les anciennes références

Assure-toi qu'aucune trace du starter ne subsiste dans ton code actif.

**Windows PowerShell :**
```powershell
git grep -n "com.laurentvrevin.androidstarter"
git grep -n "Theme.AndroidStarter"
git grep -n "AndroidStarter"
```

**macOS / Linux :**
```bash
git grep -n "com.laurentvrevin.androidstarter"
git grep -n "Theme.AndroidStarter"
git grep -n "AndroidStarter"
```

Le fichier `starter.properties` est automatiquement supprimé en cas de succès. Un fichier local `.bootstrap-complete` est créé pour marquer la fin du processus.

### Étape 11 — Créer le premier commit

Une fois le projet validé, fige les changements dans ton historique Git.

```bash
git status
git add .
git commit -m "chore: initialize MonApplication from Android starter"
git push
```

### Étape 12 — Recommencer en cas d'erreur

Si tu as fait une erreur lors de la saisie des noms :
1. Supprime ton dossier local.
2. Reclonne ton dépôt GitHub (qui est toujours dans l'état initial du template).
3. Relance le bootstrap.

> [!CAUTION]
> Supprimer uniquement le fichier `.bootstrap-complete` ne suffit pas à revenir en arrière, car les fichiers et dossiers ont déjà été renommés physiquement.

---

## 📚 Documentation Technique

1.  📂 [**Architecture & Organisation**](docs/architecture.md)
2.  🎨 [**Design System & UI**](docs/design_system.md)
3.  🌐 [**Réseau (Ktor)**](docs/network.md)
4.  💾 [**Données (Room & DataStore)**](docs/data.md)
5.  🧪 [**Stratégie de Test**](docs/testing.md)

---

*Développé avec passion pour des applications Android performantes.*
