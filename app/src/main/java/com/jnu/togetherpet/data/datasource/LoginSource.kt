package com.jnu.togetherpet.data.datasource

import android.util.Log
import com.jnu.togetherpet.data.dto.LoginRequestDTO
import com.jnu.togetherpet.data.service.LoginService
import com.jnu.model.APIException
import com.jnu.model.ErrorResponse
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginSource @Inject constructor(
    private val loginService: LoginService,
    private val gson: Gson
) {
    suspend fun login(email: String): String {
        val response = loginService.login(LoginRequestDTO(email))

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