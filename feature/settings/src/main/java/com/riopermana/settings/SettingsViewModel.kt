package com.riopermana.settings

import androidx.lifecycle.ViewModel
import com.riopermana.core.data.repositories.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    userDataRepository: UserDataRepository
) : ViewModel() {

    val darkThemeConfig = userDataRepository
        .userDataFlow
        .map { it.darkThemeConfig }

}