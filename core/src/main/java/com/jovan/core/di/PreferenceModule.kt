package com.jovan.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jovan.core.data.source.local.datastore.SettingPreferences
import com.jovan.core.data.source.local.datastore.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
class PreferenceModule {
    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences>{
        return appContext.dataStore
    }
    @Singleton
    @Provides
    fun provideSettingPreferences(dataStore: DataStore<Preferences>): SettingPreferences {
        return SettingPreferences(dataStore)
    }
}