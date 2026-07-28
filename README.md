# Language Selector App

A minimalistic Android application demonstrating how to implement in-app language selection using Jetpack Compose and the modern Android localization APIs.

## Features
- **In-App Language Switching**: Change app language dynamically without restarting the Activity.
- **Persistent Selection**: Saves the user's language preference using `SharedPreferences`.
- **Clean Architecture**: Follows a basic Domain-Data-UI structure.
- **Jetpack Compose**: Modern UI built entirely with Compose.
- **Per-App Language Preferences**: Utilizes `AppCompatDelegate.setApplicationLocales`.

## Tech Stack
- **UI**: Jetpack Compose, Material 3
- **Architecture**: ViewModel, Flow, StateFlow, UseCases
- **Storage**: SharedPreferences
- **Language Support**: English, Spanish, French, German

## How it Works
The app uses `AppCompatDelegate` to manage locales, which integrates with the system-level per-app language settings introduced in Android 13. The `LanguageRepository` handles both the available language list and the persistence of the user's choice.
# LanguageSelectorApp
