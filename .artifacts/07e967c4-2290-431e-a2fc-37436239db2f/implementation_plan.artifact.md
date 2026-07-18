# Stratégie de Vérification et de Test (Network & Data)

Ce plan détaille comment tester et valider le bon fonctionnement des couches Network (Ktor) et Data (Room) de votre starter pack. L'objectif est d'assurer que les données transitent correctement et que les erreurs sont gérées de manière prévisible.

## Objectifs de test
- **Vérifier le Network** : S'assurer que Ktor décode correctement le JSON et que le `BaseRepository` gère bien les exceptions (MockEngine).
- **Vérifier la Database** : Valider les opérations CRUD de Room (Insert/Read) dans un environnement isolé (InMemory).
- **Vérifier les Mappers** : Garantir que les transformations de données (DTO -> Entity -> Domain) sont sans perte.

## Proposed Changes

### [Component] Testing Infrastructure

#### [MODIFY] [libs.versions.toml](file:///D:/AndroidProjects/AndroidStarter/gradle/libs.versions.toml)
- Ajout de `ktor-client-mock` pour simuler des réponses API sans internet.
- Ajout de `kotlinx-coroutines-test` pour tester le code asynchrone.

#### [NEW] [NetworkTest.kt](file:///D:/AndroidProjects/AndroidStarter/app/src/test/java/com/laurentvrevin/androidstarter/core/network/NetworkTest.kt)
- Test unitaire utilisant le `MockEngine` de Ktor.
- Validation du mapping des erreurs HTTP vers `NetworkError`.

---

### [Component] Database Verification

#### [NEW] [UserDao.kt](file:///D:/AndroidProjects/AndroidStarter/app/src/main/java/com/laurentvrevin/androidstarter/data/local/dao/UserDao.kt)
- Création d'un premier DAO concret pour tester Room.

#### [NEW] [DatabaseTest.kt](file:///D:/AndroidProjects/AndroidStarter/app/src/androidTest/java/com/laurentvrevin/androidstarter/data/local/DatabaseTest.kt)
- Test d'instrumentation (AndroidTest) créant une base de données en mémoire.
- Test d'écriture et de lecture d'un `UserEntity`.

---

### [Component] Integration Example (Showcase)

#### [MODIFY] [ShowcaseScreen.kt](file:///D:/AndroidProjects/AndroidStarter/app/src/main/java/com/laurentvrevin/androidstarter/designsystem/ShowcaseScreen.kt)
- Ajout d'une section "Integration Test" qui simule un flux complet :
    1. Clic sur un bouton.
    2. Appel `safeCall` simulé.
    3. Enregistrement en base locale.
    4. Affichage du résultat provenant de la base.

## Verification Plan

### Automated Tests
- Lancement des tests unitaires : `./gradlew test`.
- Lancement des tests d'instrumentation : `./gradlew connectedAndroidTest`.

### Manual Verification
- Utilisation de la nouvelle section dans l'écran Showcase pour voir le cycle de vie de la donnée en direct.
