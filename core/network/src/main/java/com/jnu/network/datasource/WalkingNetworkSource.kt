package com.jnu.network.datasource

import android.util.Log
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalkingNetworkSource @Inject constructor(
    private val walkingService: com.jnu.network.retrofit.WalkingService,
    private val gson: Gson
) {
    suspend fun postWalkingData(
        token: String,
        walkingRequestDTO: com.jnu.network.model.WalkingRequestDTO
    ) {
        val response = walkingService.postWalkingData(
            token, walkingRequestDTO
        )

        Log.d("testt", "${response.errorBody()}, ${response.body()}, ${response.code()}")

        if (!response.isSuccessful) throw com.jnu.model.APIException(
            gson.fromJson(
                response.errorBody()?.string(),
                com.jnu.model.ErrorResponse::class.java
            )
        )
    }

    suspend fun getWalkingDataWithDate(token: String, date: String): List<com.jnu.network.model.WalkingResponseDTO>? {
        val response = walkingService.getWalkingDataWithDate(
            token, date
        )
        Log.d("testt", "${response.body()}, ${response.code()}, ${response.errorBody()?.string()}")
        return if (response.isSuccessful) {
            response.body()
        } else  {
            throw com.jnu.model.APIException(
                gson.fromJson(
                    response.errorBody()?.string(),
                    com.jnu.model.ErrorResponse::class.java
                )
            )
        }
    }
}