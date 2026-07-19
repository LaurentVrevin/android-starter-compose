# Couche Data (Persistance)

La stratégie de données du starter pack repose sur le principe de **Single Source of Truth (SSOT)**.

---

## 1. Room Database

### Base de données locale
Définie dans [**`AppDatabase.kt`**](../data/src/main/java/com/laurentvrevin/androidstarter/data/local/AppDatabase.kt) (Module `:data`). Elle centralise le stockage permanent de l'application.

### DAOs & BaseDao
Tous les DAOs doivent hériter de [`BaseDao<T>`](../data/src/main/java/com/laurentvrevin/androidstarter/data/local/dao/BaseDao.kt) pour disposer immédiatement des méthodes CRUD de base (`insert`, `update`, `delete`, `upsert`).

---

## 2. Préférences (DataStore)

Pour les données simples (thème, réglages, jetons), nous utilisons [`AppPreferences`](../data/src/main/java/com/laurentvrevin/androidstarter/data/local/AppPreferences.kt).

L'accès est typé et réactif :
```kotlin
// Lecture via Flow
val isDarkMode: Flow<Boolean?> = appPreferences.isDarkTheme

// Écriture (suspend)
appPreferences.setDarkTheme(true)
```

---

## 3. Séparation des modèles (Mappers)

Pour garder un code propre, utilisez des extensions de mapping (voir [`SampleEntity.kt`](../data/src/main/java/com/laurentvrevin/androidstarter/data/local/entities/SampleEntity.kt)) :
- **DTO** : Modèle API.
- **Entity** : Modèle Room.
- **External Model** : Modèle métier partagé (dans `:core:model`).

---

## 4. Tests

Les tests de base de données sont des **tests d'instrumentation** localisés dans [`data/src/androidTest`](../data/src/androidTest/java/com/laurentvrevin/androidstarter/data/local/DatabaseTest.kt).

> [!CAUTION]
> N'oubliez pas d'incrémenter la version de la base de données dans `AppDatabase` si vous modifiez une classe `@Entity`.
