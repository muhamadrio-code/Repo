package com.riopermana.settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.riopermana.settings.dialogs.ThemeSelectionDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by viewModels()
    private var themePreference: Preference? = null

    companion object {
        const val THEME_SELECTION_TAG = "com.dialogs.ThemeSelectionDialog"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_screen, rootKey)
        themePreference = findPreference("theme")
        themePreference?.setOnPreferenceClickListener {
            ThemeSelectionDialog().showNow(childFragmentManager, THEME_SELECTION_TAG)
            true
        }

        val languagePreference = findPreference<Preference>("language")
        languagePreference?.setOnPreferenceClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeCollector()
    }

    private fun subscribeCollector() {
        val themesDescription = arrayOf(
            getString(R.string.default_system),
            getString(R.string.light),
            getString(R.string.dark)
        )
        lifecycleScope.launch {
            viewModel.darkThemeConfig
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collect {
                    themePreference?.summary = themesDescription[it.ordinal]
                    AppCompatDelegate.setDefaultNightMode(it.mode)
                }
        }

    }

}