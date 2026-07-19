# Guide Complet du Module Data 💾

Le module `:data` est le coeur de la gestion des données de l'application. Il implémente les principes de la **Clean Architecture** avec une stratégie **Offline-First** et le pattern **Single Source of Truth (SSOT)**.

---

## 🏗 Architecture du Module

Le module est découpé en plusieurs packages spécialisés :
- **`base/`** : Abstractions transversales (ex: `BaseRepository`).
- **`local/`** : Persistance locale (Room et DataStore).
- **`remote/`** : Communication réseau (Ktor).
- **`repository/`** : Orchestration des sources de données.
- **`network/`** : Types de retour et gestion d'erreurs partagés.

---

## 🌐 Communication Réseau (Ktor)

Nous utilisons **Ktor Client 3.x** avec le moteur **OkHttp**.

### 1. Configuration Centralisée
Tout se passe dans [`KtorClientFactory`](../data/src/main/java/com/laurentvrevin/androidstarter/data/remote/KtorClientFactory.kt).
Le client est configuré avec :
- **ContentNegotiation** : JSON via Kotlinx Serialization.
- **Logging** : Activé uniquement en Debug via [`NetworkConfig`](../data/src/main/java/com/laurentvrevin/androidstarter/data/remote/NetworkConfig.kt).
- **Timeouts** : Sécurité de 15s pour éviter les appels bloqués.

### 2. Pattern de retour sécurisé
Tous les appels réseau passent par [`BaseRepository.safeCall`](../data/src/main/java/com/laurentvrevin/androidstarter/data/base/BaseRepository.kt).

> [!IMPORTANT]
> `safeCall` capture les exceptions Ktor et les transforme en [`NetworkResult<T>`](../data/src/main/java/com/laurentvrevin/androidstarter/data/network/NetworkResult.kt).
> Cela garantit que l'application ne crashe pas en cas de 404 ou de perte de réseau.

---

## 💾 Persistance Locale (Room)

### 1. Pattern BaseDao
L'interface [`BaseDao<T>`](../data/src/main/java/com/laurentvrevin/androidstarter/data/local/dao/BaseDao.kt) fournit les méthodes standards (`upsert`, `delete`, `update`). Elle utilise l'annotation `@Upsert` (disponible depuis Room 2.6) pour gérer automatiquement l'insert ou l'update en cas de conflit.

### 2. SSOT (Single Source of Truth)
Les Repositories exposent les données locales via des **Flows**.
```kotlin
fun getItems(): Flow<List<SampleItem>> = dao.getAll().map { it.toExternal() }
```
L'UI observe ce Flow. Quand on "rafraîchit" les données, on télécharge depuis l'API, on met à jour Room, et l'UI se met à jour **automatiquement** via le Flow réactif.

---

## ⚙️ Préférences (DataStore)

Nous n'utilisons plus les SharedPreferences. [`AppPreferences`](../data/src/main/java/com/laurentvrevin/androidstarter/data/local/AppPreferences.kt) fournit une API typée au-dessus de **Jetpack DataStore**.

---

## 🔄 Intégration en MVVM

Voici comment les couches communiquent :

### 1. Le Repository
Il possède une interface pour être facilement mockable pendant les tests.
```kotlin
interface MyRepository {
    fun streamData(): Flow<List<MyModel>>
    suspend fun refresh(): NetworkResult<Unit>
}
```

### 2. Le ViewModel
Il injecte le repository et expose un unique `StateFlow`.
```kotlin
class MyViewModel(private val repository: MyRepository) : ViewModel() {
    val state = repository.streamData()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onRefresh() {
        viewModelScope.launch {
            repository.refresh() // Met à jour Room, ce qui déclenche le streamData()
        }
    }
}
```

---

## 🧪 Stratégie de Test

1.  **Tests Réseau** : Utilisez `MockEngine` pour simuler des réponses JSON et vérifier le comportement de `safeCall`.
2.  **Tests Room** : Utilisez une base de données **en mémoire** (`inMemoryDatabaseBuilder`) dans `androidTest`.
3.  **Tests Repository** : Mockez le `HttpClient` et le `Dao` pour tester l'orchestration logicielle.

> [!TIP]
> Consultez le fichier [**`NetworkTest.kt`**](../data/src/test/java/com/laurentvrevin/androidstarter/data/network/NetworkTest.kt) pour voir un exemple concret de mock réseau.
