package com.jnu.network.retrofit

import com.jnu.network.model.UserResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("/api/v1/user/info")
    suspend fun getUserData(
        @Header("Authorization")token : String
    ): Response<com.jnu.network.model.UserResponseDTO>
}