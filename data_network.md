# Documentation : Network & Data (Persistance)

Cette documentation détaille le fonctionnement de la couche de données de l'application, basée sur une architecture **Offline-First** et le principe de **Seule Source de Vérité (SSOT)**.

---

## 1. Couche Réseau (Ktor)

L'application utilise **Ktor Client** pour ses communications API.

### Gestion des Erreurs (Pattern Result)
Tous les appels réseau sont encapsulés dans un `NetworkResult<T>`. Cela force la gestion explicite des cas d'erreurs :
- `Success<T>` : Contient les données récupérées.
- `Error(NetworkError)` : Contient le type d'erreur (UNAUTHORIZED, NO_INTERNET, etc.).

### Utilisation dans un Repository
```kotlin
class UserRepository(private val client: HttpClient) : BaseRepository() {
    suspend fun getUser() = safeCall { 
        client.get("profile").body<UserDto>() 
    }
}
```

---

## 2. Couche Persistance (Room & DataStore)

### Room Database
Utilisée pour le stockage de données structurées et volumineuses.
- **SSOT** : L'interface utilisateur ne doit observer que les données provenant de Room (via des `Flow`).
- **BaseDao** : Fournit des méthodes CRUD génériques (`insert`, `update`, `delete`, `upsert`).

### DataStore (PreferenceManager)
Utilisé pour les préférences utilisateur simples (ex: token d'authentification, préférences de thème).
- Fournit un accès réactif aux données via des `Flow`.

---

## 3. Flux de Données (Offline-First)

Le pattern recommandé pour synchroniser les données est le suivant :

1. **Observe** : Le ViewModel observe un `Flow` provenant de la base de données locale.
2. **Fetch** : Le Repository lance un appel réseau.
3. **Sync** : Si l'appel réussit, le Repository met à jour la base de données locale.
4. **Update** : L'UI est automatiquement mise à jour grâce à l'observation réactive de la base de données.

---

## 4. Mappers

Pour garantir une séparation stricte des responsabilités, nous utilisons des mappers :
- **DTO** (Data Transfer Object) : Modèle de l'API.
- **Entity** : Modèle de stockage Room.
- **Domain Model** : Modèle utilisé par l'UI.

Utilisez l'interface `Mapper<I, O>` pour créer vos convertisseurs de manière homogène.

---

## 6. Tests et Vérification

Le starter pack inclut une stratégie de test pour garantir la fiabilité des données.

### Tests Network (Unitaires)
Localisés dans `app/src/test/java/com/laurentvrevin/androidstarter/core/network/NetworkTest.kt`.
- Utilise `MockEngine` pour simuler des réponses API.
- Vérifie le parsing JSON et le mapping des erreurs HTTP.

### Tests Database (Instrumentation)
Localisés dans `app/src/androidTest/java/com/laurentvrevin/androidstarter/data/local/DatabaseTest.kt`.
- Utilise une base de données **en mémoire** pour isoler les tests.
- Valide les opérations d'écriture et de lecture dans Room.

Lancer les tests :
```bash
./gradlew test # Tests unitaires
./gradlew connectedAndroidTest # Tests d'instrumentation (nécessite un appareil)
```
