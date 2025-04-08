package com.jnu.network.datasource

import android.util.Log
import com.google.gson.Gson
import javax.inject.Inject

class UserSource @Inject constructor(
    private val userService: com.jnu.network.retrofit.UserService,
    private val gson: Gson
) {
    suspend fun getUserResponseDTO(token: String): com.jnu.network.model.UserResponseDTO? {
        val response = userService.getUserData(token)
        Log.d("testt", "User : ${response.body()}, ${response.errorBody()}")
        if (response.isSuccessful) {
            return response.body()
        }

        throw com.jnu.model.APIException(
            gson.fromJson(
                response.errorBody()?.string(),
                com.jnu.model.ErrorResponse::class.java
            )
        )
    }
}