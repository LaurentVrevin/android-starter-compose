# Injection de Dépendances avec Koin 💉

Le projet utilise **Koin 4.x**, un framework d'injection de dépendances (DI) léger et pragmatique, écrit en pur Kotlin.

---

## 🏗️ Initialisation

Koin est démarré dans la classe `App` (située dans le module `:app`).

```kotlin
// app/src/main/java/com/exemple/app/App.kt
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, dataModule, networkModule, /* modules des features */)
        }
    }
}
```

---

## 🧩 Les Modules

Les dépendances sont organisées par responsabilité.

### Module Technique (`:data`)
```kotlin
val networkModule = module {
    single { KtorClientFactory().create() }
}
```

### Module Feature (`:feature:template`)
```kotlin
val templateModule = module {
    // Repository injectable
    single<TemplateRepository> { TemplateRepositoryImpl(get()) }
    
    // ViewModel spécifique Koin
    viewModel { TemplateViewModel(get()) }
}
```

---

## 💉 Injection dans Compose

Utilise les fonctions dédiées pour récupérer tes instances dans l'UI.

```kotlin
@Composable
fun TaskRoute() {
    // Récupère le ViewModel injecté
    val viewModel: TaskViewModel = koinViewModel()
    
    // Récupère une dépendance simple
    val analytics: AnalyticsHelper = koinInject()
}
```

---

## 🧪 Injection dans les Tests

Koin facilite grandement les tests en permettant de remplacer une dépendance réelle par un **Fake** ou un **Mock**.

```kotlin
class MyTest : KoinTest {
    @Before
    fun setup() {
        startKoin {
            modules(module {
                single<TaskRepository> { FakeTaskRepository() } // On remplace le vrai repo
            })
        }
    }
}
```

---

## 🚫 Anti-patterns

1.  **Enorme module unique** : Ne mets pas tout dans `appModule`. Crée un module par feature.
2.  **Utiliser get() partout** : Si ton constructeur a trop de `get()`, il est peut-être temps de découper ta classe.
3.  **Inversion non respectée** : Déclare toujours tes interfaces dans le `domain` et injecte-les, plutôt que d'injecter directement l'implémentation du module `data`.

---
[Précédent : Navigation](navigation.md) | [Suivant : Données (Room & DataStore)](data.md)
