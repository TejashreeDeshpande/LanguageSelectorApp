package com.example.languageselector.domain

import javax.inject.Inject

class GetLanguagesUseCase @Inject constructor(
    private val repository: LanguageRepository
) {
    operator fun invoke(): List<AppLanguage> = repository.getAvailableLanguages()
}
