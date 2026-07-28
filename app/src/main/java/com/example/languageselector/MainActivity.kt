package com.example.languageselector

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.languageselector.ui.LanguageScreen
import com.example.languageselector.ui.LanguageViewModel
import com.example.languageselector.ui.theme.LanguageSelectorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Remove activity recreation transition to reduce flicker
        overridePendingTransition(0, 0)

        setContent {
            LanguageSelectorTheme {
                val viewModel: LanguageViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                LanguageScreen(
                    uiState = uiState,
                    onLanguageSelected = viewModel::onLanguageSelected
                )
            }
        }
    }
}
