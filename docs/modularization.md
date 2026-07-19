# Scalabilité & Modularisation

---

## 1. Structure des Modules

### `:feature:template` (Exemple)
Feature verticale complète servant de modèle.
- **Responsabilité** : Logique métier et UI d'un écran.
- **Dépendances** : `:core`, `:designsystem`, `:data`.

---

## 2. Créer une nouvelle Feature

1. Créez un module `:feature:mony-feature`.
2. Appliquez le plugin `androidstarter.android.library.compose`.
3. Implémentez les couches `presentation`, `domain`, `data`.
4. Enregistrez le module Koin dans `:app`.
5. Ajoutez la route dans `AppNavHost`.
