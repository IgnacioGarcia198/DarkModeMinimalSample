package com.ignacio.daynightsample

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    private var defaultDarkModeChoicePreference: ListPreference? = null
    private var themeChoicePreference: ListPreference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        defaultDarkModeChoicePreference = findPreference(DEFAULT_DARK_MODE_VARIANT_KEY)
        themeChoicePreference = findPreference(THEME_CHOICE_KEY)


        themeChoicePreference?.setOnPreferenceChangeListener { _, newValue ->
            val choice = ThemeHelper.ThemeChoice.valueOf(newValue as String)
            val defaultDarkModeChoice = getDefaultDarkModeVariantValue()
            ThemeHelper.applyTheme(choice, defaultDarkModeChoice)
            true
        }

        defaultDarkModeChoicePreference?.setOnPreferenceChangeListener { _, newValue ->
            val choice = ThemeHelper.DefaultDarkModeVariant.valueOf(newValue as String)
            val themeChoice = getThemeChoiceValue()
            ThemeHelper.applyTheme(themeChoice, choice)
            true
        }
    }

    fun getThemeChoiceValue(): ThemeHelper.ThemeChoice =
        ThemeHelper.ThemeChoice.valueOf(themeChoicePreference?.value ?: ThemeHelper.ThemeChoice.default.name)

    fun getDefaultDarkModeVariantValue(): ThemeHelper.DefaultDarkModeVariant =
        ThemeHelper.DefaultDarkModeVariant.valueOf(
            defaultDarkModeChoicePreference?.value
                ?: ThemeHelper.DefaultDarkModeVariant.system.name)
}