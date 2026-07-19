# Guide de Renommage du Projet

Ce guide explique comment transformer ce boilerplate pour votre propre application.

## 1. Utilisation du script (Windows)

Un script PowerShell est disponible pour automatiser le remplacement du package name et du nom de l'application dans les fichiers.

```powershell
./scripts/rename_project.ps1 -NewPackageName "com.votre.app" -NewAppName "MonSuperProjet"
```

## 2. Renommage manuel (Recommandé via IDE)

Si vous préférez le faire manuellement ou si vous êtes sur macOS/Linux :

### Package Name
1.  Dans Android Studio, clic droit sur le package `com.laurentvrevin.androidstarter` dans la vue Project.
2.  Sélectionnez **Refactor -> Rename**.
3.  Choisissez **Rename package** (pas seulement le dossier final).
4.  Entrez votre nouveau package name.
5.  Vérifiez les occurrences dans `build.gradle.kts` (namespaces et applicationId).

### Nom du Projet
1.  Changez `rootProject.name` dans `settings.gradle.kts`.
2.  Changez le nom dans `strings.xml` (ressource `app_name`).

## 3. Checklist de Validation

Après le renommage, effectuez ces étapes :
- [ ] `./gradlew clean`
- [ ] Synchroniser Gradle avec l'IDE.
- [ ] Vérifier que `applicationId` dans `:app/build.gradle.kts` est correct.
- [ ] Lancer les tests unitaires : `./gradlew test`.
- [ ] Lancer l'application pour vérifier que les composants s'affichent toujours.
