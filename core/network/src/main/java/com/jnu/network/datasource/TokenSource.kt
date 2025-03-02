package com.jnu.network.datasource

interface TokenSource {
    fun saveToken(token: String)
    fun getTokenOrThrow(): String
    fun hasToken(): Boolean
}