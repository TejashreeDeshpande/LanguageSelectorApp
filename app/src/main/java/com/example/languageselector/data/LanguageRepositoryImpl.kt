package com.example.languageselector.data

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.languageselector.R
import com.example.languageselector.domain.AppLanguage
import com.example.languageselector.domain.LanguageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.milliseconds

@Singleton
class LanguageRepositoryImpl @Inject constructor() : LanguageRepository {

    private val repositoryScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var selectionJob: Job? = null

    private val _selectedLanguageCode = MutableStateFlow(
        getCurrentLocaleCode()
    )
    override val selectedLanguageCode: StateFlow<String> = _selectedLanguageCode

    override fun getAvailableLanguages(): List<AppLanguage> = listOf(
        AppLanguage("en", R.string.language_en, "English"),
        AppLanguage("es", R.string.language_es, "Español"),
        AppLanguage("fr", R.string.language_fr, "Français"),
        AppLanguage("de", R.string.language_de, "Deutsch")
    )

    override fun selectLanguage(languageCode: String) {
        if (languageCode == _selectedLanguageCode.value) return

        _selectedLanguageCode.value = languageCode
        
        // Cancel any pending language change
        selectionJob?.cancel()
        selectionJob = repositoryScope.launch {
            // Use a small delay before applying the locale change.
            // This allows the UI to update the selection state and play animations
            // before the Activity is recreated.
            delay(100.milliseconds)
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(languageCode)
            )
        }
    }

    private fun getCurrentLocaleCode(): String {
        val locales = AppCompatDelegate.getApplicationLocales()
        if (locales.isEmpty) return DEFAULT_LANGUAGE
        
        // Try to match the primary language tag with our supported languages
        val primaryLocale = locales.get(0) ?: return DEFAULT_LANGUAGE
        val language = primaryLocale.language
        
        return if (language in listOf("en", "es", "fr", "de")) {
            language
        } else {
            DEFAULT_LANGUAGE
        }
    }

    private companion object {
        const val DEFAULT_LANGUAGE = "en"
    }
}
