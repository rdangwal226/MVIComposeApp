# 🐾 Kotlin Compose MVI App

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.0-blue.svg)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-blue?logo=android)](https://developer.android.com/jetpack/compose)
[![MVI](https://img.shields.io/badge/MVI-Architecture-orange.svg)](https://en.wikipedia.org/wiki/Model–view–intent)
[![Dagger Hilt](https://img.shields.io/badge/Dagger%20Hilt-DI-brightgreen?logo=dagger)](https://dagger.dev/hilt)

A hands-on Android project demonstrating **MVI (Model–View–Intent)**, **Clean Architecture**, and **SOLID** principles using **Jetpack Compose** and **Dagger Hilt** for dependency injection.


## ✨ Motivation

This project aims to:

- Explore **MVI** with Jetpack Compose.
- Apply **Clean Architecture** layering.
- Practice **SOLID** design principles.
- Use **Dagger Hilt** for dependency injection.

---

## 🐘 Features

| UI | Domain | Data |
| --- | --- | --- |
| • Compose screens with state-hoisting<br>• Material 3 theming<br>• Skeleton versions for all Composable components | • Use cases orchestrating business logic | • REST consumption via Retrofit<br>• Future local cache (Room) |
| • Loading / error placeholders | • Immutable result modeling | • Repository & data abstraction |
| • Animal detail taxonomy view | • Separation of concerns | • Dispatcher configuration |

---

## 🔧 Tech Stack & Key Dependencies

| Category | Library |
|----------|---------|
| **Language** | Kotlin 2.0.0, Coroutines |
| **UI** | Jetpack Compose, Material3 |
| **Architecture** | ViewModel, StateFlow, MVI |
| **DI** | Dagger Hilt |
| **Networking** | Retrofit, OkHttp, Moshi |
| **Testing (coming soon)** | JUnit5, Turbine, MockK |
| **Build** | Gradle KTS, GitHub Actions (CI planned) |

---

## 📁 Project Structure

<pre>
📦 kotlin-compose-mvi-animals-app
 ┣ 📂 core                 ← Common utilities, models, result wrappers, extensions
 ┣ 📂 data                 ← DTOs, services, repository implementation, mappers
 ┣ 📂 di                   ← Hilt modules for DI
 ┣ 📂 domain              ← Business logic: use cases and interfaces
 ┣ 📂 presentation        ← Compose UI, MVI ViewModels and state
 ┃ ┣ 📂 components         ← Shared UI components
 ┃ ┣ 📂 features           ← Modular screens
 ┃ ┃ ┣ 📂 animal_details
 ┃ ┃ ┗ 📂 animal_list
 ┃ ┗ 📂 navigation         ← App navigation logic
 ┣ 📂 ui.theme            ← Theme and color definitions
 ┣ 🧩 AnimalApplication.kt ← App entry point with Hilt config
 ┗ 🧩 MainActivity.kt      ← Activity hosting Compose content
</pre>

---

## 🚀 Getting Started

```bash
git clone https://github.com/felippeneves/kotlin-compose-mvi-animals-app.git
cd kotlin-compose-mvi-animals-app
```

Open in Android Studio (Iguana or newer) and run the `app` module on an emulator or physical device.

---


## 🤝 Contributing

Feel free to fork, suggest improvements or open a PR! All contributions are welcome 🚀
