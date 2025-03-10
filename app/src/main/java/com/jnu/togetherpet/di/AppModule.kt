package com.jnu.togetherpet.di

import com.jnu.togetherpet.AppNavigatorImpl
import com.jnu.ui.AppNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppNavigator(appNavigator: AppNavigatorImpl): AppNavigator = appNavigator
}