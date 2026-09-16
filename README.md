# Android Starter Pack 🚀

Bienvenue dans ton **Android Starter Pack**.

Ce projet est une base de démarrage moderne et robuste conçue pour accélérer le développement d’applications Android professionnelles tout en conservant une architecture propre, modulaire et testable.

---

## 🛠️ Stack technique

- **Kotlin 2.x**
- **Jetpack Compose**
- **Material 3**
- **Koin**
- **Ktor Client**
- **Room**
- **DataStore**
- **Navigation Compose**
- **Coroutines / Flow / StateFlow**
- **Architecture multi-module**
- **Gradle Convention Plugins**
- **Version Catalog**
- **JUnit / Compose UI Tests**
- **ktlint**

---

## 📖 Documentation

Pour comprendre le starter plus en détail :

1. 📂 [**Vision d’ensemble**](docs/overview.md)
2. 🏛️ [**Architecture**](docs/architecture.md)
3. 📦 [**Modularisation**](docs/modularization.md)
4. 🚀 [**Guide de création de Feature**](docs/feature_guide.md)
5. 🎨 [**Design System**](docs/design_system.md)
6. 🔄 [**UI State & UDF**](docs/ui_state.md)
7. 🗺️ [**Navigation**](docs/navigation.md)
8. 💉 [**Injection de dépendances**](docs/dependency_injection.md)
9. 💾 [**Données**](docs/data.md)
10. 🌐 [**Réseau**](docs/network.md)
11. 🧪 [**Tests**](docs/testing.md)
12. 🛠️ [**Build Logic**](docs/build_logic.md)
13. 🚩 [**Bootstrap**](docs/bootstrap.md)
14. 🆘 [**Dépannage**](docs/troubleshooting.md)

---

## 🚀 Démarrage rapide

Pour transformer ce starter en ton propre projet :

1. Sur GitHub, clique sur **Use this template**.
2. Choisis **Create a new repository**.
3. Donne un nom à ton nouveau repository, par exemple `MonApp`.
4. Copie l’URL Git du nouveau repository.
5. Dans Android Studio, utilise **Clone Repository** et colle l’URL.
6. Une fois le projet ouvert, lance d’abord un Dry Run du bootstrap.

### Windows

```powershell
.\gradlew.bat --console=plain bootstrapProject "-PdryRun=true"
```

### macOS / Linux

```bash
./gradlew --console=plain bootstrapProject -PdryRun=true
```

Renseigne ensuite les informations demandées.

Exemple :

```text
Project Name: MonApp
Display Name: Mon App
Package Name: com.prenomnom.monapp
```

Si le Dry Run se termine par :

```text
BUILD SUCCESSFUL
```

tu peux lancer le vrai bootstrap.

### Windows

```powershell
.\gradlew.bat --console=plain bootstrapProject
```

### macOS / Linux

```bash
./gradlew --console=plain bootstrapProject
```

Utilise les mêmes valeurs, puis confirme avec `y`.

Enfin :

1. fais un **Gradle Sync** ;
2. vérifie que le projet compile.

### Windows

```powershell
.\gradlew.bat :app:assembleDebug
```

### macOS / Linux

```bash
./gradlew :app:assembleDebug
```

Le projet est prêt lorsque tu obtiens :

```text
BUILD SUCCESSFUL
```

> [!IMPORTANT]
> Pour le guide complet, les captures d’écran, les explications du Dry Run, le JDK, le dépannage et les cas d’erreur, consulte :
>
> [**Guide détaillé du Bootstrap**](docs/bootstrap.md)

---

## 🛠️ Commandes principales

### Bootstrap

```bash
./gradlew bootstrapProject
```

### Compilation

```bash
./gradlew :app:assembleDebug
```

### Tests

```bash
./gradlew test
```

### Lint

```bash
./gradlew ktlintCheck
```

---

## ❤️ Android Starter Pack

Ce starter a été conçu pour fournir une base Android :

- propre ;
- moderne ;
- maintenable ;
- testable ;
- modulaire ;
- réutilisable.

L’objectif est de supprimer le travail répétitif de démarrage afin de te permettre de te concentrer rapidement sur ton application.