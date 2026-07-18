# Couche Réseau (Ktor)

L'infrastructure réseau repose sur **Ktor Client**, configuré pour la résilience et la facilité de débogage.

---

## 1. Configuration du Client

Le `HttpClient` est créé via la [**`KtorClientFactory`**](../app/src/main/java/com/laurentvrevin/androidstarter/data/remote/KtorClientFactory.kt) :
- **Engine** : OkHttp.
- **Serialization** : JSON (Kotlinx Serialization).
- **Logging** : Logs complets en debug.
- **Timeouts** : 15 secondes par défaut.

---

## 2. Pattern de Résilience : `NetworkResult`

Tous les appels API doivent retourner un [`NetworkResult<T>`](../app/src/main/java/com/laurentvrevin/androidstarter/core/network/NetworkResult.kt).

```kotlin
sealed interface NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>
    data class Error(val error: NetworkError) : NetworkResult<Nothing>
}
```

### Types d'erreurs gérés
Le système traduit automatiquement les codes HTTP en [`NetworkError`](../app/src/main/java/com/laurentvrevin/androidstarter/core/network/NetworkError.kt) :
- `UNAUTHORIZED` (401)
- `NOT_FOUND` (404)
- `SERVER_ERROR` (5xx)
- `NO_INTERNET` (IOException)

---

## 3. Utilisation dans un Repository

Héritez de `BaseRepository` pour bénéficier de la méthode `safeCall`.

```kotlin
class ProductRepository(private val client: HttpClient) : BaseRepository() {
    
    suspend fun getProducts(): NetworkResult<List<ProductDto>> = safeCall {
        client.get("products").body()
    }
}
```

---

## 4. Tests Unitaires (Mocking)

Pour tester vos appels réseau sans serveur, utilisez le `MockEngine` de Ktor. Un exemple complet est disponible dans [**`NetworkTest.kt`**](../app/src/test/java/com/laurentvrevin/androidstarter/core/network/NetworkTest.kt).

```kotlin
val client = HttpClient(MockEngine) {
    engine {
        addHandler { request ->
            respond(jsonString, HttpStatusCode.OK)
        }
    }
}
```

> [!TIP]
> Utilisez toujours `safeCall` pour envelopper vos appels Ktor afin de garantir qu'aucune exception technique ne remonte jusqu'à l'UI.
