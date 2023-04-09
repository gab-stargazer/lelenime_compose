package com.lelestacia.more.screen.settings

import com.lelestacia.common.display_style.DisplayStyle

sealed class SettingScreenEvent {
    data class UpdateDisplayStylePreferences(val displayStyle: DisplayStyle) : SettingScreenEvent()
    object DisplayStylePreferencesMenuStateChanged: SettingScreenEvent()
    data class UpdateDarkModePreferences(val darkModePreferences: Int) : SettingScreenEvent()
    object DarkModePreferencesMenuStateChanged: SettingScreenEvent()
    data class UpdateDynamicThemePreferences(val dynamicThemePreferences: Boolean) :
        SettingScreenEvent()

    object IsUpdateDynamicThemePreferencesStateChanged: SettingScreenEvent()
}