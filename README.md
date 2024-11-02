# NewsApp

<img width="1000" src="https://raw.githubusercontent.com/artexhibit/NewsApp/main/resources/picture.png" alt="Image">

[🇷🇺 Russian Version](./resources/README-RUS.md)

## About

**NewsApp** is an Android app developed to explore and apply modern Android development practices. The app leverages the latest libraries and architectural patterns, delivering a structured, efficient, and scalable code.

## Features

- Fetches and displays the latest news using `NewsAPI`;
- Supports infinite news scrolling;
- Saves user preferences and provide a dark theme.

## Technologies & Architecture

NewsApp incorporates a range of industry-standard libraries and design principles, including:

- **UI**:
    - **Jetpack Compose**: For building declarative UIs.

- **Architecture**:
    - **Clean Architecture**: Ensuring a scalable and maintainable codebase;
    - **MVVM & MVI**: Utilizing both Model-View-ViewModel (MVVM) and Model-View-Intent (MVI) patterns.

- **Data Management**:
    - **Paging 3**: Efficiently loads paginated data;
    - **Room**: Local database for offline data storage;
    - **DataStore Preferences**: Persists user settings and preferences.

- **Networking**:
    - **Retrofit**: Fetches data from APIs.

- **Dependency Injection**:
    - **Dagger Hilt**: Simplifies dependency management.