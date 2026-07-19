# Guide Complet du Module Data 💾

Le module `:data` est le coeur de la gestion des données.

---

## 💾 Persistance Locale (Room)

Les fichiers liés à la feature Template sont isolés pour être faciles à retirer :
[`data/database/template/`](../data/src/main/java/com/laurentvrevin/androidstarter/data/local/database/template/)

### SSOT (Single Source of Truth)
Les Repositories exposent les données locales via des **Flows**.
L'UI observe ce Flow. Quand Room est mis à jour, l'UI réagit automatiquement.

---

## ⚙️ Préférences (DataStore)

[`AppPreferences`](../data/src/main/java/com/laurentvrevin/androidstarter/data/local/AppPreferences.kt) fournit une API typée avec un enum `ThemeMode`.
