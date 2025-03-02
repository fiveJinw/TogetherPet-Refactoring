package com.jnu.network.datasource

import android.util.Log
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MissingSource @Inject constructor(
    private val missingService: com.jnu.network.retrofit.MissingService,
    private val gson: Gson
) {
    suspend fun registerMissing(
        token: String,
        missingRegisterRequestDTO: com.jnu.network.model.MissingRegisterRequestDTO
    ) {
        val response = missingService.registerMissing(token, missingRegisterRequestDTO)

        if (!response.isSuccessful) {
            throw com.jnu.model.APIException(
                gson.fromJson(
                    response.errorBody()?.string(),
                    com.jnu.model.ErrorResponse::class.java
                )
            )
        }
    }

    suspend fun getMissingNearBy(
        latitude: Double,
        longitude: Double
    ): List<com.jnu.network.model.MissingResponseDTO> {
        val response = missingService.getMissingNearBy(latitude, longitude)

        if (response.isSuccessful) {
            return response.body()!!
        }

        throw com.jnu.model.APIException(
            gson.fromJson(
                response.errorBody()?.string(),
                com.jnu.model.ErrorResponse::class.java
            )
        )
    }

    suspend fun getMissingByMissingId(
        missingId: Number
    ): com.jnu.network.model.MissingDetailResponseDTO {
        val response = missingService.getMissingByMissingId(missingId)
        Log.d("MissingSource", "API call response: $response")

        if (response.isSuccessful) {
            val responseBody = response.body()
            Log.d("MissingSource", "API call successful, response body: $responseBody")
            return response.body()!!
        }

        Log.e("MissingSource", "API call failed, error: ${response.errorBody()?.string()}")
        throw com.jnu.model.APIException(
            gson.fromJson(
                response.errorBody()?.string(),
                com.jnu.model.ErrorResponse::class.java
            )
        )
    }
}