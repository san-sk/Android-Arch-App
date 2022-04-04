package com.san.archapp.di

import android.content.Context
import android.content.SharedPreferences
import com.san.archapp.utils.AppConstants
import com.san.archapp.preferences.AppPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PrefModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(AppConstants.APP_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideSessionManager(sharedPreferences: SharedPreferences) =
        AppPrefManager(sharedPreferences)

}