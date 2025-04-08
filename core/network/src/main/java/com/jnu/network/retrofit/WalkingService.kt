package com.jnu.network.retrofit

import com.jnu.network.model.WalkingRequestDTO
import com.jnu.network.model.WalkingResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface WalkingService {
    @POST("/api/v1/walk")
    suspend fun postWalkingData(
        @Header("Authorization")token : String,
        @Body walkingRequestDTO : com.jnu.network.model.WalkingRequestDTO
    ) : Response<Unit>

    @GET("/api/v1/walk/paths")
    suspend fun getWalkingDataWithDate(
        @Header("Authorization")token : String,
        @Query("date") date: String
    ): Response<List<com.jnu.network.model.WalkingResponseDTO>>
}