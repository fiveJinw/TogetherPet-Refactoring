package com.jnu.network.retrofit

import com.jnu.model.dto.MissingRegisterRequestDTO
import retrofit2.Response
import retrofit2.http.*

interface MissingService {

    @POST("/api/v1/missing")
    suspend fun registerMissing(
        @Header("Authorization") token: String,
        @Body missingRegisterRequestDTO: MissingRegisterRequestDTO
    ): Response<Unit>

    @GET("/api/v1/missing")
    suspend fun getMissingNearBy(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): Response<List<com.jnu.network.model.MissingResponseDTO>>

    @GET("/api/v1/missing/{missing-id}")
    suspend fun getMissingByMissingId(
        @Path("missing-id") missingId: Number
    ): Response<com.jnu.network.model.MissingDetailResponseDTO>
}