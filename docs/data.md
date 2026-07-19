# Couche Data (Persistance)

---

## 1. Room Database

### Base de données locale
Définie dans [**`AppDatabase.kt`**](../data/src/main/java/com/laurentvrevin/androidstarter/data/local/AppDatabase.kt).

### DAOs & BaseDao
Héritez de [`BaseDao<T>`](../data/src/main/java/com/laurentvrevin/androidstarter/data/local/dao/BaseDao.kt) pour le CRUD standard.

---

## 2. Préférences (DataStore)

Utilisez [`AppPreferences`](../data/src/main/java/com/laurentvrevin/androidstarter/data/local/AppPreferences.kt) pour les réglages typés.

---

## 3. Séparation des modèles

Utilisez des extensions de mapping (voir [`TemplateEntity.kt`](../data/src/main/java/com/laurentvrevin/androidstarter/data/local/database/template/TemplateEntity.kt)).
- **Entity** : Modèle Room.
- **External Model** : Modèle métier (dans `feature/.../domain`).
