# Couche Réseau (Ktor)

L'application utilise **Ktor Client** pour ses appels réseau, configuré de manière résiliente et sécurisée.

---

## 1. Architecture

La couche réseau est centralisée dans le module `:data`.

- **`KtorClientFactory`** : Centralise la configuration (Serialization, Logging, Timeouts, Base URL).
- **`NetworkConfig`** : Permet d'injecter des paramètres différents selon le build type (Debug/Release).
- **`BaseRepository`** : Fournit la méthode `safeCall` pour sécuriser tous les appels API.

---

## 2. Pattern de retour

Tous les appels API doivent être encapsulés dans un `NetworkResult<T>` via `safeCall`.

```kotlin
suspend fun fetchData(): NetworkResult<MyDto> = safeCall {
    client.get("endpoint").body()
}
```

### Avantages :
1.  **Gestion des erreurs typée** : Oblige à traiter les cas d'erreur (`Unauthorized`, `ServerError`, `NoInternet`).
2.  **Sécurité Coroutines** : Propagera correctement la `CancellationException`.
3.  **Logging** : Les erreurs techniques sont logguées proprement sans polluer l'UI.

---

## 3. Configuration de production

- **Logging** : Désactivé en production pour des raisons de sécurité et de performance.
- **Timeouts** : Fixés à 15s par défaut pour éviter les appels bloqués.
- **Serialization** : Utilise Kotlinx Serialization configuré avec `ignoreUnknownKeys = true`.

---

## 4. Tests

Utilisez le `MockEngine` de Ktor pour simuler des réponses API. 
Voir l'exemple dans [**`NetworkTest.kt`**](../data/src/test/java/com/laurentvrevin/androidstarter/data/network/NetworkTest.kt).
