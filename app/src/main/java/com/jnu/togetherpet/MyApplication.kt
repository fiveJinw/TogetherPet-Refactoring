package com.jnu.togetherpet

import android.app.Application
import android.util.Log
import com.jnu.model.APIException
import com.kakao.sdk.common.KakaoSdk
import com.kakao.vectormap.KakaoMapSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
        KakaoMapSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)

        Thread.setDefaultUncaughtExceptionHandler { thread, exception ->
            if (exception is com.jnu.model.APIException) {
                Log.e("API Exception", "Error code : " + exception.errorResponse.code)
                Log.e("API Exception", "Error Message : " + exception.errorResponse.message)
            } else{
                exception.printStackTrace()
            }
        }
    }
}