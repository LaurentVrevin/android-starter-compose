# Couche Data (Persistance)

La stratégie de données du starter pack repose sur le principe de **Single Source of Truth (SSOT)**.

---

## 1. Room Database

### Base de données locale
Définie dans [**`AppDatabase.kt`**](../app/src/main/java/com/laurentvrevin/androidstarter/data/local/AppDatabase.kt). Elle centralise le stockage permanent de l'application.

### DAOs & BaseDao
Tous les DAOs doivent hériter de [`BaseDao<T>`](../app/src/main/java/com/laurentvrevin/androidstarter/data/local/dao/BaseDao.kt) pour disposer immédiatement des méthodes CRUD de base :
- `insert(obj)`
- `update(obj)`
- `delete(obj)`
- `upsert(obj)` (Insert ou Update)

---

## 2. Préférences (DataStore)

Pour les données simples (settings, tokens), nous utilisons [`PreferenceManager`](../app/src/main/java/com/laurentvrevin/androidstarter/data/local/PreferenceManager.kt) basé sur **Jetpack DataStore**.

L'accès est entièrement réactif via des `Flow` :
```kotlin
// Lecture
val isDarkMode: Flow<Boolean> = preferenceManager.getBoolean("dark_mode")

// Écriture (suspend)
preferenceManager.saveBoolean("dark_mode", true)
```

---

## 3. Séparation des modèles (Mappers)

Pour garder un code propre, ne mélangez jamais les couches. Utilisez l'interface [`Mapper<I, O>`](../app/src/main/java/com/laurentvrevin/androidstarter/core/util/Mapper.kt) :

1.  **DTO** : Modèle reçu de l'API (Ktor).
2.  **Entity** : Modèle stocké en base (Room).
3.  **Domain** : Modèle utilisé par l'UI (Kotlin pur).

---

## 4. Tests de Persistance

Les tests de base de données sont des **tests d'instrumentation** localisés dans [`androidTest/`](../app/src/androidTest/java/com/laurentvrevin/androidstarter/data/local/DatabaseTest.kt). Ils utilisent une base de données en mémoire (`InMemoryDatabase`) pour ne pas polluer les données réelles de l'application.

> [!CAUTION]
> N'oubliez pas d'incrémenter la version de la base de données dans `AppDatabase` si vous modifiez une classe `@Entity`.
