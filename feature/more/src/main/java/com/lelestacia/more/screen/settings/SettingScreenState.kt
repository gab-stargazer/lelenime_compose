package com.lelestacia.more.screen.settings

import com.lelestacia.common.display_style.DisplayStyle

data class SettingScreenState(
    val isDisplayStylePreferencesMenuOpened: Boolean = false,
    val displayStylePreferences: DisplayStyle = DisplayStyle.CARD,
    val isDarkModePreferencesMenuOpened: Boolean = false,
    val darkModePreferences: Int = 0,
    val isDynamicThemePreferencesMenuOpened: Boolean = false,
    val dynamicThemePreferences: Boolean = true,
)
