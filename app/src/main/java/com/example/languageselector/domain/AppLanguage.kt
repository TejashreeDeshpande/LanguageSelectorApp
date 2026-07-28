package com.example.languageselector.domain

import androidx.annotation.StringRes

data class AppLanguage(
    val code: String,
    @StringRes val displayNameRes: Int,
    val nativeName: String
)
