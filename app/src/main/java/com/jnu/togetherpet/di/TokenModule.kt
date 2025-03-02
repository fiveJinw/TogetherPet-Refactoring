package com.jnu.togetherpet.di

import com.jnu.network.datasource.TokenSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TokenModule {

    @Binds
    @Singleton
    abstract fun bindTokenSource(
        tokenRepository: com.jnu.data.repo.TokenRepository
    ): TokenSource
}