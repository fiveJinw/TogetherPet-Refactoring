package com.jnu.data.repo

import com.jnu.network.datasource.LoginSource
import com.jnu.network.datasource.TokenSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val loginSource: com.jnu.network.datasource.LoginSource,
    private val tokenSource: TokenSource
) {
    suspend fun login(email: String) {
        tokenSource.saveToken(loginSource.login(email))
    }
}