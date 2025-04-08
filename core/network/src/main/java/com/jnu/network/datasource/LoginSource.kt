package com.jnu.network.datasource

import android.util.Log
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginSource @Inject constructor(
    private val loginService: com.jnu.network.retrofit.LoginService,
    private val gson: Gson
) {
    suspend fun login(email: String): String {
        val response = loginService.login(com.jnu.network.model.LoginRequestDTO(email))

        if (response.isSuccessful) {
            Log.e("Token", response.headers()["Authorization"].toString())
            return response.headers()["Authorization"].toString()
        }

        throw com.jnu.model.APIException(
            gson.fromJson(
                response.errorBody()?.string(),
                com.jnu.model.ErrorResponse::class.java
            )
        )
    }
}