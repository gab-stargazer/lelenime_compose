package com.lelestacia.database.user_preferences

import kotlinx.coroutines.flow.Flow

interface IUserPreferencesService {
    suspend fun getUserDisplayStyle(): Flow<Int>
    suspend fun updateUserDisplayStyle(displayStylePreferences: Int)
    suspend fun getUserTheme(): Flow<Int>
    suspend fun updateUserTheme(themePreferences: Int)
    suspend fun getUserPreferenceOnDynamicTheme(): Flow<Boolean>
    suspend fun updateUserPreferenceOnDynamicTheme(dynamicPreferences: Boolean)
}
