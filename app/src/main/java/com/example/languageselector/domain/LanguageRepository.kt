package com.example.languageselector.domain

import kotlinx.coroutines.flow.StateFlow

interface LanguageRepository {
    val selectedLanguageCode: StateFlow<String>
    fun getAvailableLanguages(): List<AppLanguage>
    fun selectLanguage(languageCode: String)
}
