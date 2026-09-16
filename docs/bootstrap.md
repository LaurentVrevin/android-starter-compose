# 🚩 Guide de Bootstrap

Ce guide explique pas à pas comment transformer l’**Android Starter Pack** en ton propre projet Android.

Le bootstrap automatise le renommage du projet afin d’éviter de modifier manuellement :

- le nom du projet ;
- le nom visible de l’application ;
- le package Kotlin / Java ;
- les namespaces Android ;
- l’`applicationId` ;
- le thème ;
- les dossiers source ;
- les références internes ;
- les schémas Room.

L’objectif est d’obtenir rapidement un nouveau projet propre et compilable à partir du starter.

---

## ✅ Avant de commencer

Assure-toi d’avoir :

- Git installé ;
- Android Studio installé ;
- un JDK compatible avec ton projet ;
- une connexion Internet pour le premier téléchargement des dépendances Gradle.

Le projet utilise le **Gradle Wrapper**, il n’est donc pas nécessaire d’installer Gradle séparément.

> [!IMPORTANT]
> Le bootstrap est prévu pour être exécuté **une seule fois** sur un nouveau projet créé depuis le template.
>
> Ne lance pas le bootstrap directement dans le repository original du starter.

---

## 1. Créer un nouveau repository depuis le template

Sur GitHub, ouvre le repository de l’Android Starter Pack.

Clique sur :

**Use this template**

puis :

**Create a new repository**

Donne un nom à ton nouveau repository.

Exemple :

```text
MonApp
```

Choisis ensuite si le repository doit être :

- Public ;
- Private.

Puis clique sur :

**Create repository**

> 📸 **Capture recommandée**
>
> Bouton **Use this template** puis écran **Create a new repository**.

> [!IMPORTANT]
> Ne travaille pas directement dans le repository original du starter.
>
> `Use this template` crée un nouveau repository indépendant que tu peux modifier librement.

---

## 2. Copier l’URL Git du nouveau repository

Dans ton nouveau repository GitHub :

1. clique sur **Code** ;
2. sélectionne **HTTPS** ;
3. copie l’URL du repository.

Exemple :

```text
https://github.com/prenomnom/MonApp.git
```

> 📸 **Capture recommandée**
>
> Bouton **Code → HTTPS → Copy**.

---

## 3. Cloner le repository dans Android Studio

Ouvre Android Studio.

Depuis l’écran d’accueil, clique sur :

**Clone Repository**

ou :

**Get from VCS**

selon ta version d’Android Studio.

Colle l’URL GitHub :

```text
https://github.com/prenomnom/MonApp.git
```

Choisis le dossier local dans lequel le projet sera cloné.

Exemple :

```text
D:\AndroidProjects\MonApp
```

Clique sur :

**Clone**

Android Studio télécharge alors le repository et ouvre le projet.

> 📸 **Capture recommandée**
>
> Fenêtre **Clone Repository** avec l’URL GitHub.

---

## 4. Attendre la première configuration Android Studio

Lors du premier lancement, Android Studio peut prendre plusieurs minutes pour :

- télécharger Gradle ;
- télécharger les dépendances ;
- compiler le `build-logic` ;
- indexer le projet ;
- effectuer le premier Gradle Sync.

Selon la machine et la connexion Internet, cela peut prendre environ :

```text
1 à 5 minutes
```

ou davantage sur un environnement neuf.

> [!NOTE]
> Il est normal que le premier lancement soit nettement plus long que les suivants.

> [!NOTE]
> Des lignes commençant par :
>
> ```text
> w:
> ```
>
> correspondent généralement à des **warnings**.
>
> Un warning n’est pas forcément une erreur.
>
> En cas d’échec réel, Gradle affichera généralement :
>
> ```text
> BUILD FAILED
> ```

---

## 5. Vérifier le JDK Gradle

Si Android Studio affiche un message comme :

```text
Invalid Gradle JDK configuration
```

ouvre :

```text
File
→ Settings
→ Build, Execution, Deployment
→ Build Tools
→ Gradle
```

Vérifie le champ :

```text
Gradle JDK
```

Sélectionne un JDK compatible avec la version d’Android Gradle Plugin utilisée par le starter.

Android Studio peut utiliser son propre **JetBrains Runtime**.

> 📸 **Capture recommandée**
>
> `Settings → Build Tools → Gradle → Gradle JDK`

Tu peux aussi vérifier Gradle depuis le terminal.

### Windows

```powershell
.\gradlew.bat -version
```

### macOS / Linux

```bash
./gradlew -version
```

Une sortie normale ressemble à :

```text
Gradle 8.x

Launcher JVM: ...
Daemon JVM: ...
```

---

## 6. Ouvrir le terminal Android Studio

Dans Android Studio :

```text
View
→ Tool Windows
→ Terminal
```

ou utilise directement l’onglet :

```text
Terminal
```

en bas de l’IDE.

Le terminal doit être positionné à la racine du projet.

Exemple Windows :

```text
PS D:\AndroidProjects\MonApp>
```

À cet emplacement, tu dois trouver :

```text
gradlew.bat
```

> 📸 **Capture recommandée**
>
> Terminal Android Studio ouvert à la racine du projet.

---

## 7. Faire un Dry Run avant le vrai bootstrap

Avant de modifier réellement les fichiers du projet, effectue toujours une simulation.

Cette simulation s’appelle un :

**Dry Run**

Le Dry Run montre ce que le script va modifier, mais **n’applique aucun changement**.

### Windows

```powershell
.\gradlew.bat --console=plain bootstrapProject "-PdryRun=true"
```

### macOS / Linux

```bash
./gradlew --console=plain bootstrapProject -PdryRun=true
```

---

## 8. Renseigner les informations du projet

Le bootstrap va demander plusieurs informations.

Exemple :

```text
Project Name: MonApp
Display Name: Mon App
Package Name: com.prenomnom.monapp
```

### Project Name

Nom technique du projet Gradle.

Exemple :

```text
MonApp
```

### Display Name

Nom visible de l’application sur l’appareil Android.

Exemple :

```text
Mon App
```

### Package Name

Identifiant technique de l’application.

Exemple :

```text
com.prenomnom.monapp
```

Le package doit respecter un format comme :

```text
com.nom.monapp
```

Utilise :

- des lettres minuscules ;
- des chiffres si nécessaire ;
- des points pour séparer les différentes parties.

Évite :

- les espaces ;
- les accents ;
- les caractères spéciaux.

Exemple valide :

```text
com.prenomnom.monapp
```

Exemple invalide :

```text
com.prénom nom.mon app
```

---

## 9. Vérifier le résultat du Dry Run

Le script doit afficher un résumé similaire à :

```text
Bootstrapping project...

Project Name:
AndroidStarter -> MonApp

Display Name:
Android Starter -> Mon App

Package Name:
com.prenomnom.androidstarter
-> com.prenomnom.monapp

Theme Name:
Theme.AndroidStarter
-> Theme.MonApp

[DRY RUN MODE - No changes will be applied]
```

Puis une liste de fichiers :

```text
[DRY RUN] Would update: ...
[DRY RUN] Would move content from: ...
```

Le résultat attendu à la fin est :

```text
BUILD SUCCESSFUL
```

> [!IMPORTANT]
> Tant que tu es en mode Dry Run, aucun fichier n’est réellement modifié.

> 📸 **Capture recommandée**
>
> Résumé du Dry Run avec `BUILD SUCCESSFUL`.

---

## 10. Lancer le vrai bootstrap

Lorsque le Dry Run est correct et se termine par :

```text
BUILD SUCCESSFUL
```

lance le vrai bootstrap.

### Windows

```powershell
.\gradlew.bat --console=plain bootstrapProject
```

### macOS / Linux

```bash
./gradlew --console=plain bootstrapProject
```

Entre exactement les mêmes valeurs que pendant le Dry Run.

Exemple :

```text
Project Name: MonApp
Display Name: Mon App
Package Name: com.prenomnom.monapp
```

Le script affiche ensuite un résumé des changements qui vont être appliqués.

---

## 11. Confirmer le bootstrap avec `y`

Avant d’appliquer les changements, le bootstrap attend une confirmation.

Normalement, tu verras :

```text
Confirm applying these changes? (y/N)
```

Tape :

```text
y
```

puis appuie sur **Entrée**.

Le `y` signifie simplement :

```text
Yes
```

c’est-à-dire que tu autorises le bootstrap à appliquer les changements affichés.

> [!TIP]
> ### Le bootstrap semble bloqué à `0% EXECUTING` ?
>
> Si tu vois quelque chose comme :
>
> ```text
> <-------------> 0% EXECUTING
> > :bootstrapProject
> ```
>
> et que rien ne semble se passer, **le bootstrap n’est probablement pas bloqué**.
>
> Dans certains terminaux, notamment le terminal intégré d’Android Studio, le message de confirmation peut être masqué par l’affichage de progression de Gradle.
>
> Le script attend simplement ta confirmation.
>
> **Tape :**
>
> ```text
> y
> ```
>
> puis appuie sur **Entrée**.
>
> Le bootstrap devrait alors reprendre immédiatement.
>
> En résumé :
>
> ```text
> 0% EXECUTING
> > :bootstrapProject
>
> ↓
>
> tape y
>
> ↓
>
> Entrée
>
> ↓
>
> le bootstrap continue
> ```
>
> Si le résumé du bootstrap a déjà été affiché et que tu es bien dans le **bootstrap réel**, tu peux donc taper `y` même si la question de confirmation n’est pas visible.

---

## 12. Attendre la fin du bootstrap

Après confirmation, le script modifie réellement le projet.

Il va notamment :

- mettre à jour les fichiers Gradle ;
- modifier l’`applicationId` ;
- modifier les namespaces ;
- renommer les packages ;
- déplacer les fichiers Kotlin / Java ;
- modifier le thème ;
- mettre à jour les ressources ;
- modifier les références internes ;
- renommer les schémas Room.

Sur un projet déjà synchronisé, cela peut prendre seulement quelques secondes.

Lors du premier lancement, Gradle peut cependant prendre plusieurs minutes.

> [!IMPORTANT]
> Ne ferme pas Android Studio ou le terminal pendant l’exécution.

Le résultat attendu est :

```text
BUILD SUCCESSFUL
```

---

## 13. Vérifier que le bootstrap a réussi

Après un bootstrap réussi, le projet doit utiliser les nouvelles valeurs.

Par exemple :

```text
Project Name:
MonApp

Application Name:
Mon App

Package:
com.prenomnom.monapp
```

Le bootstrap crée également une protection empêchant son exécution accidentelle une deuxième fois.

> [!WARNING]
> Ne supprime pas cette protection dans le but de relancer le bootstrap.
>
> Si tu souhaites créer une nouvelle application, repars d’un nouveau repository créé depuis le template.

---

## 14. Faire un Gradle Sync

Après le bootstrap, retourne dans Android Studio.

Lance :

```text
File
→ Sync Project with Gradle Files
```

Attends que le Gradle Sync soit terminé.

> 📸 **Capture recommandée**
>
> Bouton ou menu **Sync Project with Gradle Files**.

---

## 15. Vérifier que l’application compile

Dans le terminal Android Studio :

### Windows

```powershell
.\gradlew.bat :app:assembleDebug
```

### macOS / Linux

```bash
./gradlew :app:assembleDebug
```

Le résultat attendu est :

```text
BUILD SUCCESSFUL
```

Si le build passe, ton nouveau projet est correctement initialisé.

---

## 16. Créer un premier commit Git

Une fois le projet compilé avec succès, crée un premier point de sauvegarde Git.

Vérifie d’abord les modifications :

```bash
git status
```

Ajoute les fichiers :

```bash
git add .
```

Crée le commit :

```bash
git commit -m "chore: bootstrap project"
```

Puis envoie-le sur GitHub :

```bash
git push
```

Tu disposes maintenant d’un point de retour propre correspondant au projet juste après son initialisation.

---

## ⏱️ Combien de temps cela peut prendre ?

Les durées dépendent fortement de la machine et de la connexion Internet.

| Étape | Durée habituelle |
|---|---:|
| Clone Git | quelques secondes à 1 min |
| Premier Gradle Sync | 1 à 5 min |
| Dry Run après premier Sync | quelques secondes |
| Bootstrap réel | quelques secondes |
| `assembleDebug` | quelques secondes à quelques minutes |

Le tout premier lancement peut être nettement plus long si Gradle doit télécharger de nombreux fichiers.

---

# 🆘 Problèmes fréquents

## Le premier build semble bloqué

Le premier lancement Gradle peut prendre plusieurs minutes.

Gradle peut être en train de :

- télécharger des dépendances ;
- compiler le `build-logic` ;
- créer ses caches.

Attends quelques minutes avant de considérer qu’il est réellement bloqué.

---

## Le bootstrap reste à `0% EXECUTING`

Si tu as déjà vu le résumé du bootstrap et que tu observes :

```text
<-------------> 0% EXECUTING
> :bootstrapProject
```

**ne ferme pas le terminal et ne relance pas immédiatement la commande.**

Le bootstrap attend probablement simplement ta confirmation.

Tape :

```text
y
```

puis appuie sur **Entrée**.

Le bootstrap devrait reprendre.

> [!TIP]
> Le message :
>
> ```text
> Confirm applying these changes? (y/N)
> ```
>
> peut parfois être masqué par la progression Gradle.
>
> Si tu es bien dans le bootstrap réel et que le résumé des modifications a déjà été affiché :
>
> **`0% EXECUTING` → tape `y` → Entrée.**

---

## J’ai tapé `y` mais rien ne se passe

Vérifie que le terminal a bien le focus.

Clique dans le terminal, puis tape à nouveau :

```text
y
```

et appuie sur **Entrée**.

---

## Android Studio affiche `Invalid Gradle JDK configuration`

Va dans :

```text
File
→ Settings
→ Build, Execution, Deployment
→ Build Tools
→ Gradle
```

et vérifie :

```text
Gradle JDK
```

---

## Je vois des messages commençant par `w:`

Exemple :

```text
w: kotlinOptions(...) is deprecated
```

Le préfixe :

```text
w:
```

signifie généralement :

```text
warning
```

Un warning ne signifie pas forcément que le build a échoué.

Regarde toujours la dernière partie de la sortie.

Succès :

```text
BUILD SUCCESSFUL
```

Échec :

```text
BUILD FAILED
```

---

## Je vois `Deprecated Gradle features were used`

Ce message indique qu’une partie du projet ou d’un plugin utilise une API Gradle dépréciée.

Ce n’est pas nécessairement bloquant pour le build actuel.

Si la commande termine par :

```text
BUILD SUCCESSFUL
```

le build a réussi.

---

## Le bootstrap dit qu’il a déjà été exécuté

Le bootstrap est conçu pour être lancé une seule fois.

Si le projet a déjà été initialisé, ne tente pas de forcer une nouvelle exécution.

Pour créer une nouvelle application :

1. retourne sur le repository GitHub du starter ;
2. utilise **Use this template** ;
3. crée un nouveau repository ;
4. recommence le processus.

---

## La commande PowerShell interprète mal les propriétés `-P`

Sous Windows / PowerShell, préfère mettre les propriétés Gradle complètes entre guillemets.

Exemple :

```powershell
.\gradlew.bat bootstrapProject "-PprojectName=MonApp" "-PappDisplayName=Mon App" "-PpackageName=com.prenomnom.monapp" "-PdryRun=true"
```

Cela évite que PowerShell ou Gradle interprète incorrectement une partie des propriétés.

---

# 🤖 Mode non interactif

Il est également possible de fournir directement les valeurs au bootstrap.

### Dry Run sous Windows

```powershell
.\gradlew.bat --console=plain bootstrapProject "-PprojectName=MonApp" "-PappDisplayName=Mon App" "-PpackageName=com.prenomnom.monapp" "-PdryRun=true"
```

Si le Dry Run est correct, tu peux exécuter le bootstrap réel :

```powershell
.\gradlew.bat --console=plain bootstrapProject "-PprojectName=MonApp" "-PappDisplayName=Mon App" "-PpackageName=com.prenomnom.monapp"
```

Puis confirmer avec :

```text
y
```

> [!TIP]
> Si la commande reste ensuite affichée à :
>
> ```text
> 0% EXECUTING
> > :bootstrapProject
> ```
>
> tape simplement :
>
> ```text
> y
> ```
>
> puis **Entrée**.

---

# 🛠️ Commandes utiles

## Vérifier Gradle

### Windows

```powershell
.\gradlew.bat -version
```

### macOS / Linux

```bash
./gradlew -version
```

---

## Dry Run interactif

### Windows

```powershell
.\gradlew.bat --console=plain bootstrapProject "-PdryRun=true"
```

### macOS / Linux

```bash
./gradlew --console=plain bootstrapProject -PdryRun=true
```

---

## Bootstrap réel interactif

### Windows

```powershell
.\gradlew.bat --console=plain bootstrapProject
```

### macOS / Linux

```bash
./gradlew --console=plain bootstrapProject
```

---

## Compiler l’application

### Windows

```powershell
.\gradlew.bat :app:assembleDebug
```

### macOS / Linux

```bash
./gradlew :app:assembleDebug
```

---

## Lancer les tests

### Windows

```powershell
.\gradlew.bat test
```

### macOS / Linux

```bash
./gradlew test
```

---

## Vérifier le style Kotlin

### Windows

```powershell
.\gradlew.bat ktlintCheck
```

### macOS / Linux

```bash
./gradlew ktlintCheck
```

---

# ✅ Checklist finale

Avant de commencer le développement de ton application, vérifie :

- [ ] le projet a été créé avec **Use this template** ;
- [ ] le nouveau repository a été cloné dans Android Studio ;
- [ ] le premier Gradle Sync est terminé ;
- [ ] le Dry Run du bootstrap termine par `BUILD SUCCESSFUL` ;
- [ ] les valeurs `Project Name`, `Display Name` et `Package Name` sont correctes ;
- [ ] le bootstrap réel a été confirmé avec `y` ;
- [ ] si Gradle est resté à `0% EXECUTING`, tu as essayé `y` puis Entrée ;
- [ ] le bootstrap réel termine par `BUILD SUCCESSFUL` ;
- [ ] le Gradle Sync après bootstrap est terminé ;
- [ ] `:app:assembleDebug` termine par `BUILD SUCCESSFUL` ;
- [ ] un premier commit Git a été créé et poussé.

Ton projet est alors prêt pour le développement. 🚀