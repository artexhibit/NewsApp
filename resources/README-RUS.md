# NewsApp

<img width="1000" src="https://raw.githubusercontent.com/artexhibit/NewsApp/main/resources/picture.png" alt="Image">

[🇬🇧 Английская версия](./README-RUS.md)

## О проекте

**NewsApp** — это Android-приложение, созданное для изучения и получения практики разработки на платформе Android. Приложение использует новейшие библиотеки и архитектурные шаблоны, для поддержания структурированного, эффективного и масштабируемого кода.

## Возможности

- Получение и отображение последних новостей с использованием `NewsAPI`;
- Поддержка бесконечной прокрутки новостей;
- Сохранение пользовательских настроек и поддержка темной темы.

## Технологии и архитектура

NewsApp использует ряд библиотек и архитектурных подходов, принятых в отрасли:

- **Интерфейс**:
    - **Jetpack Compose**: Для создания декларативных пользовательских интерфейсов.

- **Архитектура**:
    - **Чистая архитектура**: Обеспечение масштабируемости и поддерживаемости кода;
    - **MVVM и MVI**: Использование моделей Model-View-ViewModel (MVVM) и Model-View-Intent (MVI).

- **Управление данными**:
    - **Paging 3**: Эффективная загрузка данных по страницам;
    - **Room**: Локальная база данных для хранения данных в оффлайн-режиме;
    - **DataStore Preferences**: Сохранение настроек и предпочтений пользователя.

- **Работа с сетью**:
    - **Retrofit**: Получение данных через API.

- **Внедрение зависимостей**:
    - **Dagger Hilt**: Упрощает управление зависимостями.