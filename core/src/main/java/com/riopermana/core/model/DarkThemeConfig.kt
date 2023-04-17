package com.riopermana.core.model

import androidx.appcompat.app.AppCompatDelegate

enum class DarkThemeConfig(val mode: Int) {
    FOLLOW_SYSTEM(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    LIGHT(AppCompatDelegate.MODE_NIGHT_NO),
    DARK(AppCompatDelegate.MODE_NIGHT_YES)
}