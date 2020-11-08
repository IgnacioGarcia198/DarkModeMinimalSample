package com.ignacio.daynightsample

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

const val THEME_CHOICE_KEY = "themeChoice"
const val DEFAULT_DARK_MODE_VARIANT_KEY = "defaultDarkModeVariantChoice"
@Suppress("unused")
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themeChoiceString = sharedPreferences.getString(THEME_CHOICE_KEY, ThemeHelper.ThemeChoice.default.name) ?: ThemeHelper.ThemeChoice.default.name
        val defaultDarkModeVariantString = sharedPreferences.getString(DEFAULT_DARK_MODE_VARIANT_KEY, ThemeHelper.DefaultDarkModeVariant.system.name) ?: ThemeHelper.DefaultDarkModeVariant.system.name
        val themeChoice = ThemeHelper.ThemeChoice.valueOf(themeChoiceString)
        val defaultDarkModeVariant = ThemeHelper.DefaultDarkModeVariant.valueOf(defaultDarkModeVariantString)
        ThemeHelper.applyTheme(themeChoice, defaultDarkModeVariant)
    }
}