# Guide de Renommage du Projet

## 1. Utilisation des scripts

- **Windows** : `./scripts/rename_project.ps1 -NewPackageName "com.votre.app" -NewAppName "MonApp"`
- **Linux/macOS** : `./scripts/rename_project.sh "com.votre.app" "MonApp"`

## 2. Checklist

- [ ] Lancer le script.
- [ ] `./gradlew clean`.
- [ ] Renommer la structure des dossiers dans l'IDE (Refactor -> Move).
- [ ] Vérifier `strings.xml`.
