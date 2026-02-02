# Comments App (Technical Assessment)

This project is an Android application built to demonstrate modern Android development practices. It fetches and displays a list of comments and user details from the [JSONPlaceholder API](https://jsonplaceholder.typicode.com/).

The app follows **Clean Architecture** principles and is built using **Jetpack Compose** for the UI, **Hilt** for dependency injection, and **Retrofit** for networking. It includes unit tests for the Data, Domain, and Presentation layers.

## Table of Contents

- [Architecture](#architecture)
- [Tech Stack & Libraries](#tech-stack--libraries)
- [Features](#features)
- [Handling Configuration Changes](#handling-configuration-changes)
- [Setup](#setup)
- [Unit Testing](#unit-testing)
- [Screenshots](#screenshots)
- [Contact](#contact)

## Architecture

The project strictly follows **Clean Architecture** with a **MVVM** pattern, separating concerns into three distinct layers:

1.  **Domain Layer (Kotlin Pure)**:
    - Contains **Entities** (Data classes like `Comment`).
    - Defines **Repository Interfaces**.
    - Contains **Use Cases** (e.g., `GetAllCommentsUseCase`) which encapsulate business logic (such as sorting comments alphabetically).

2.  **Data Layer**:
    - Manages network operations using **Retrofit**.
    - Implements Repository interfaces.
    - Maps **DTOs** (Data Transfer Objects) to Domain Entities using Mappers.

3.  **Presentation Layer (App)**:
    - **Jetpack Compose**: For building declarative UI.
    - **ViewModels**: Manages UI state using `StateFlow` / `MutableState`.
    - **Hilt**: Injects dependencies (UseCases) into ViewModels.

## Tech Stack & Libraries

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Toolkit**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Dependency Injection**: [Hilt](https://dagger.dev/hilt/)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & [Gson](https://github.com/google/gson)
- **Concurrency**: [Coroutines](https://developer.android.com/kotlin/coroutines) & [Flow](https://developer.android.com/kotlin/flow)
- **Navigation**: [Compose Navigation](https://developer.android.com/guide/navigation/navigation-compose)
- **Testing**:
    - [JUnit 4](https://junit.org/junit4/): Standard unit testing framework.
    - [MockK](https://mockk.io/): For creating mocks in Kotlin.
    - [Coroutines Test](https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test): For testing suspend functions and flows.

## Features

- **Comments List**: Fetches a list of comments from the API.
- **Sorting**: Automatically sorts comments alphabetically by body content.
- **Detail View**: Shows detailed information for a specific comment, including the associated User data.
- **State Management**: Handles Loading, Success, and Error states (e.g., Network failures).
- **Navigation**: Seamless transition between the List screen and Details screen.

## Handling Configuration Changes

The application robustly handles configuration changes (such as device rotation) using **Jetpack ViewModel**.

- **Lifecycle Awareness**: The `CommentViewModel` is lifecycle-aware and survives the destruction and recreation of the Activity during rotation.
- **State Persistence**: The UI state is held in a `MutableState` (or `StateFlow`) within the ViewModel. When the screen rotates:
    1. The Activity is destroyed.
    2. The ViewModel instance is **retained** in memory.
    3. The Activity is recreated and reconnects to the *existing* ViewModel.
    4. The UI immediately renders the existing data without re-fetching from the network, ensuring a smooth user experience.

## Setup

To run this project:

1.  **Clone the repository**:
    ```bash
    git clone [https://github.com/AbdullhGaber/CommentsAPI.git](https://github.com/AbdullhGaber/CommentsAPI.git)
    cd CommentsAPI
    ```

2.  **Open in Android Studio**:
    - Open Android Studio and select **Open**.
    - Navigate to the cloned directory.

3.  **Sync & Build**:
    - Wait for Gradle to sync dependencies.
    - Select an emulator or physical device (Min SDK 24).
    - Click **Run**.

## Unit Testing

This project emphasizes testing across all layers.

- **Data Layer**: Tests Mappers (DTO to Entity) and JSON parsing.
- **Domain Layer**: Tests Use Cases to ensure business logic (like sorting) works correctly.
- **Presentation Layer**: Tests ViewModels to ensure UI State updates correctly based on UseCase results.

To run the tests:
1.  Right-click on the `com.example` package in the `(test)` directory of any module (app, data, domain).
2.  Select **Run Tests**.

## Screenshots

| List Screen | Detail Screen |
|:---:|:---:|
| ![List Screen](https://via.placeholder.com/250x500?text=List+Screen) | ![Detail Screen](https://via.placeholder.com/250x500?text=Detail+Screen) |

## Contact

- **Developer**: Abdullh Gaber
- **Email**: [drabdullh2002.1@gmail.com](mailto:drabdullh2002.1@gmail.com)
- **GitHub**: [AbdullhGaber](https://github.com/AbdullhGaber)
