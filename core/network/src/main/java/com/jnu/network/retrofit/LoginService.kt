package com.jnu.network.retrofit

import com.jnu.network.model.LoginRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/api/v1/login")
    suspend fun login(
        @Body body: com.jnu.network.model.LoginRequestDTO
    ): Response<Unit>

}