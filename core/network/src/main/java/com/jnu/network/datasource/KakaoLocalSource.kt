package com.jnu.network.datasource

import android.util.Log
import com.jnu.network.BuildConfig
import com.jnu.network.model.AddressDTO
import com.jnu.network.retrofit.KakaoLocalService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KakaoLocalSource @Inject constructor(
    private val kakaoLocalService: KakaoLocalService
) {
    suspend fun latLngToAddress(longitude: String, latitude: String): List<AddressDTO> {
        val result = kakaoLocalService.changeLatLngToAddress(
            BuildConfig.KAKAO_LOCAL_API_KEY,
            longitude,
            latitude
        )
        Log.d("testt", "${result.body().toString()}, ${result.errorBody().toString()}")
        Log.d("testt", "Body: ${result.body().toString()}, Error: ${result.errorBody()?.string()}")
        return result.body()?.documents ?: emptyList()
    }
}
