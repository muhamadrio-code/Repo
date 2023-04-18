package com.riopermana.settings.dialogs

import androidx.lifecycle.*
import com.riopermana.core.data.repositories.UserDataRepository
import com.riopermana.core.model.DarkThemeConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeSelectionDialogViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    private var config = DarkThemeConfig.FOLLOW_SYSTEM

    val darkThemeConfig = userDataRepository.userDataFlow
        .map { it.darkThemeConfig }
        .onEach {
            config = it
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = DarkThemeConfig.FOLLOW_SYSTEM
        )

    fun getCurrentPosition(): Int {
        return config.ordinal
    }

    fun onSelectTheme(position: Int) {
        config = DarkThemeConfig.values()[position]
    }

    fun onSubmitTheme() {
        viewModelScope.launch {
            userDataRepository.setDarkThemeConfig(config)
        }
    }

}