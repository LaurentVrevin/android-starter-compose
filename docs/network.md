# Couche Réseau (Ktor)

L'infrastructure réseau repose sur **Ktor Client**, configuré pour la résilience et la facilité de débogage.

---

## 1. Configuration du Client

Le `HttpClient` est créé via la [**`KtorClientFactory`**](../data/src/main/java/com/laurentvrevin/androidstarter/data/remote/KtorClientFactory.kt) dans le module `:data` :
- **Engine** : OkHttp.
- **Serialization** : JSON (Kotlinx Serialization).
- **Logging** : Logs complets en debug.
- **Timeouts** : 15 secondes par défaut.

---

## 2. Pattern de Résilience : `NetworkResult`

Tous les appels API doivent retourner un [`NetworkResult<T>`](../core/src/main/java/com/laurentvrevin/androidstarter/core/network/NetworkResult.kt).

```kotlin
sealed interface NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>
    data class Error(val error: NetworkError) : NetworkResult<Nothing>
}
```

### Types d'erreurs gérés
Le système traduit automatiquement les codes HTTP en [`NetworkError`](../core/src/main/java/com/laurentvrevin/androidstarter/core/network/NetworkError.kt) via le `BaseRepository`.

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

## 4. Fiabilité & Debug

- **Logs** : En mode debug, toutes les requêtes/réponses sont affichées dans Logcat sous le tag `HttpClient`.
- **Tests** : Utilisez le `MockEngine` pour simuler des réponses API. Voir [**`NetworkTest.kt`**](../core/src/test/java/com/laurentvrevin/androidstarter/core/network/NetworkTest.kt).

> [!TIP]
> Utilisez toujours `safeCall` pour envelopper vos appels Ktor afin de garantir qu'aucune exception technique ne remonte jusqu'à l'UI.
