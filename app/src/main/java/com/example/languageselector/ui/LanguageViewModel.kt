package com.example.languageselector.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.languageselector.domain.GetLanguagesUseCase
import com.example.languageselector.domain.LanguageRepository
import com.example.languageselector.domain.SelectLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    repository: LanguageRepository,
    getLanguages: GetLanguagesUseCase,
    private val selectLanguage: SelectLanguageUseCase
) : ViewModel() {

    private val availableLanguages = getLanguages()

    val uiState = repository.selectedLanguageCode
        .map { selectedCode ->
            LanguageUiState(
                languages = availableLanguages,
                selectedLanguageCode = selectedCode
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = LanguageUiState(
                languages = availableLanguages,
                selectedLanguageCode = repository.selectedLanguageCode.value
            )
        )

    fun onLanguageSelected(languageCode: String) {
        viewModelScope.launch {
            selectLanguage(languageCode)
        }
    }
}
