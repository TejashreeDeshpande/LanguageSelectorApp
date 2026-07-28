package com.example.languageselector.ui

import androidx.compose.runtime.Immutable
import com.example.languageselector.domain.AppLanguage

@Immutable
data class LanguageUiState(
    val languages: List<AppLanguage> = emptyList(),
    val selectedLanguageCode: String = "en"
)
