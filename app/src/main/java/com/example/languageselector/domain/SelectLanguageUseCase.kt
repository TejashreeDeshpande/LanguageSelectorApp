package com.example.languageselector.domain

import javax.inject.Inject

class SelectLanguageUseCase @Inject constructor(
    private val repository: LanguageRepository
) {
    operator fun invoke(languageCode: String) {
        repository.selectLanguage(languageCode)
    }
}
