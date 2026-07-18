# Stratégie de Test

Le starter pack est configuré pour supporter une pyramide de tests robuste, garantissant que les couches critiques (Network et Data) sont toujours fonctionnelles.

---

## 1. Tests Network (Unitaires)

Nous testons la couche réseau sans serveur réel grâce au **`MockEngine`** de Ktor.

### Localisation
[`core/src/test/java/.../network/NetworkTest.kt`](../core/src/test/java/com/laurentvrevin/androidstarter/core/network/NetworkTest.kt)

### Ce qui est testé
- Le décodage correct du JSON vers les DTOs.
- La transformation des codes HTTP (401, 404, 500) en erreurs métiers `NetworkError`.
- Le bon fonctionnement de la méthode `safeCall`.

---

## 2. Tests Database (Instrumentation)

Les tests Room nécessitent un environnement Android. Ils s'exécutent donc dans le dossier `androidTest`.

### Localisation
[`data/src/androidTest/java/.../local/DatabaseTest.kt`](../data/src/androidTest/java/com/laurentvrevin/androidstarter/data/local/DatabaseTest.kt)

### Isolation
Nous utilisons une base de données **en mémoire** (`Room.inMemoryDatabaseBuilder`) qui est détruite après chaque test. Cela garantit que les tests n'interfèrent pas avec les données réelles de l'application.

---

## 3. Lancer les tests

### Via la ligne de commande
```bash
# Lancer tous les tests unitaires
./gradlew test

# Lancer les tests d'instrumentation (nécessite un appareil ou émulateur)
./gradlew connectedAndroidTest
```

### Via Android Studio
Faites un clic droit sur le dossier `java` d'un module et sélectionnez **"Run 'Tests in...'"**.

---

## 4. Outils utilisés
- **JUnit 4** : Framework de test standard.
- **Kotlinx Coroutines Test** : Pour tester le code asynchrone (`runTest`).
- **Ktor MockEngine** : Simulation de serveur HTTP.
- **Room Testing** : Support officiel de Room pour les tests.
