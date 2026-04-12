# Kotlin Multiplatform (KMP) - Networking & Persistence
A modern Kotlin Multiplatform project focused on implementing a robust data layer that shares logic between platforms. This project demonstrates the integration of asynchronous networking, local caching, and dependency injection in a shared environment.

### 🚀 Technical Stack
This project leverages the official Kotlin Multiplatform libraries to ensure a clean, scalable, and testable codebase:

* Kotlin Multiplatform - Core framework for sharing code across platforms.
* Ktor - For handling HTTP requests and asynchronous networking.
* Koin - A pragmatic lightweight dependency injection framework.
* Kotlinx Coroutines - Managing background tasks and concurrency.

### 🏗️ Architecture
The project follows a Clean Architecture pattern within the shared module to maximize maintainability:
* Shared Module: Contains the business logic, DTOs, and the Repository pattern.
* Data Layer: Handles the orchestration between the Ktor network client and the SQLDelight local database.
* DI: Managed by Koin to provide seamless injection across the shared and platform-specific modules.

### ⚠️ Current Platform Status
Platform Support: Android Only > Due to hardware limitations (development is currently being conducted on a Windows environment), this project is configured and tested exclusively for Android. While the shared logic is platform-agnostic, the iOS target is currently disabled/untested as it requires macOS for compilation.

### 🛠️ Getting Started
Prerequisites
* Android Studio Ladybug or newer.
* JDK 17 or higher.
* Windows / Linux / macOS (Note: iOS build is only available on macOS).

Installation
* Clone this repository
* Open the project in Android Studio.
* Sync Gradle and ensure all dependencies are downloaded.
* Run the androidApp configuration on an emulator or physical device.

### 📈 Future Roadmap
* [x] Implement Unit Testing for the shared Repository.
* [x] Add Pagination support for the Ktor network layer.
