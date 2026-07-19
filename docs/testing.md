# Stratégie de Test

Le starter pack est configuré pour supporter une pyramide de tests robuste.

---

## 1. Tests Network (Unitaires)

Nous testons la couche réseau sans serveur réel grâce au **`MockEngine`** de Ktor.

### Localisation
[`data/src/test/java/.../network/NetworkTest.kt`](../data/src/test/java/com/laurentvrevin/androidstarter/data/network/NetworkTest.kt)

### Ce qui est testé
- Le décodage correct du JSON.
- La transformation des erreurs HTTP en `NetworkError`.
- Le bon fonctionnement de `safeCall` (incluant la propagation de l'annulation).

---

## 2. Tests Database (Instrumentation)

Les tests Room s'exécutent dans un environnement Android réel ou simulé.

### Localisation
[`data/src/androidTest/java/.../local/DatabaseTest.kt`](../data/src/androidTest/java/com/laurentvrevin/androidstarter/data/local/DatabaseTest.kt)

### Isolation
Nous utilisons une base de données **en mémoire** qui est recréée à chaque test pour garantir l'indépendance des résultats.

---

## 3. Tests Logic (ViewModels)

Chaque feature doit avoir ses propres tests unitaires pour valider les changements d'états.
Voir [**`SampleViewModelTest.kt`**](../feature/sample/src/test/java/com/laurentvrevin/androidstarter/feature/sample/SampleViewModelTest.kt).

---

## 4. Lancer les tests

### Via la ligne de commande
```bash
# Lancer tous les tests unitaires de tous les modules
./gradlew test

# Lancer les tests d'instrumentation (nécessite un appareil ou émulateur)
./gradlew connectedAndroidTest
```
