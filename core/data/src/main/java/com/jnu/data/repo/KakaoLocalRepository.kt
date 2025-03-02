package com.jnu.data.repo

import android.util.Log
import com.jnu.network.datasource.KakaoLocalSource
import com.jnu.network.model.AddressDTO
import com.kakao.vectormap.LatLng
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KakaoLocalRepository @Inject constructor(
    private val kakaoLocalSource: com.jnu.network.datasource.KakaoLocalSource
) {
    suspend fun latLngToAddress(latLng: LatLng) : com.jnu.network.model.AddressDTO {
        Log.d("testt", "longitude : ${latLng.longitude}, latitude : ${latLng.latitude}")
        return kakaoLocalSource.latLngToAddress(latLng.longitude.toString(), latLng.latitude.toString()).first()
    }
}