package com.lelestacia.domain.usecases.settings

import kotlinx.coroutines.flow.Flow

interface IUserPreferencesUseCases {
    suspend fun getUserDisplayStyle(): Flow<Int>
    suspend fun updateUserDisplayStyle(displayStylePreferences: Int)
    suspend fun getUserTheme(): Flow<Int>
    suspend fun updateUserTheme(themePreferences: Int)
    suspend fun getUserPreferenceOnDynamicTheme(): Flow<Boolean>
    suspend fun updateUserPreferenceOnDynamicTheme(dynamicPreferences: Boolean)
}
