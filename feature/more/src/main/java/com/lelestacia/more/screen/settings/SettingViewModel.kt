package com.lelestacia.more.screen.settings

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.common.display_style.DisplayStyle
import com.lelestacia.domain.usecases.settings.IUserPreferencesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val useCases: IUserPreferencesUseCases
) : ViewModel() {

    val settingScreenState = MutableStateFlow(SettingScreenState())

    fun onEvent(event: SettingScreenEvent) {
        when (event) {
            SettingScreenEvent.DisplayStylePreferencesMenuStateChanged -> settingScreenState.update {
                it.copy(
                    isDisplayStylePreferencesMenuOpened = !it.isDisplayStylePreferencesMenuOpened
                )
            }

            is SettingScreenEvent.UpdateDarkModePreferences -> viewModelScope.launch {
                useCases.updateUserTheme(
                    themePreferences = event.darkModePreferences
                )
            }

            SettingScreenEvent.DarkModePreferencesMenuStateChanged -> settingScreenState.update {
                it.copy(
                    isDarkModePreferencesMenuOpened = !it.isDarkModePreferencesMenuOpened
                )
            }

            is SettingScreenEvent.UpdateDisplayStylePreferences -> viewModelScope.launch {
                Log.d(TAG, "onEvent: Update Display Style Scope Launched")
                useCases.updateUserDisplayStyle(
                    displayStylePreferences = when (event.displayStyle) {
                        DisplayStyle.CARD -> 1
                        DisplayStyle.COMPACT_CARD -> 2
                        DisplayStyle.LIST -> 3
                    }
                )
            }

            is SettingScreenEvent.UpdateDynamicThemePreferences -> viewModelScope.launch {
                useCases.updateUserPreferenceOnDynamicTheme(
                    dynamicPreferences = event.dynamicThemePreferences
                )
            }

            SettingScreenEvent.IsUpdateDynamicThemePreferencesStateChanged -> settingScreenState.update {
                it.copy(
                    isDynamicThemePreferencesMenuOpened = !it.isDynamicThemePreferencesMenuOpened
                )
            }
        }
    }

    init {
        viewModelScope.launch {
            useCases.getUserDisplayStyle().collectLatest { style ->
                Log.d("Settings ViewModel", "observeDisplayStyle: $style")
                settingScreenState.update {
                    it.copy(
                        displayStylePreferences = when (style) {
                            1 -> DisplayStyle.CARD
                            2 -> DisplayStyle.COMPACT_CARD
                            else -> DisplayStyle.LIST
                        }
                    )
                }
            }

            viewModelScope.launch {
                useCases.getUserTheme().collectLatest { theme ->
                    settingScreenState.update {
                        it.copy(
                            darkModePreferences = theme
                        )
                    }
                }
            }

            viewModelScope.launch {
                useCases.getUserPreferenceOnDynamicTheme().collectLatest { dynamicTheme ->
                    settingScreenState.update {
                        it.copy(
                            dynamicThemePreferences = dynamicTheme
                        )
                    }
                }
            }
        }
    }
}