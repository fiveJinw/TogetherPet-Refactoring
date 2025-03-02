package com.jnu.network.di

import com.jnu.network.BuildConfig
import com.jnu.network.retrofit.KakaoLocalService
import com.jnu.network.retrofit.LoginService
import com.jnu.network.retrofit.MissingService
import com.jnu.network.retrofit.RegisterService
import com.jnu.network.retrofit.ReportService
import com.jnu.network.retrofit.UserService
import com.jnu.network.retrofit.WalkingService
//import com.jnu.togetherpet.security.SelfSigningHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
//    fun provideRetrofit(selfSigningHelper: SelfSigningHelper): Retrofit {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("kakaoLocalRetrofit")
    fun provideKakaoLocalRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.KAKAO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): com.jnu.network.retrofit.LoginService {
        return retrofit.create(com.jnu.network.retrofit.LoginService::class.java)
    }

    @Provides
    @Singleton
    fun provideRegisterService(retrofit: Retrofit): com.jnu.network.retrofit.RegisterService {
        return retrofit.create(com.jnu.network.retrofit.RegisterService::class.java)
    }

    @Provides
    @Singleton
    fun provideMissingService(retrofit: Retrofit): com.jnu.network.retrofit.MissingService {
        return retrofit.create(com.jnu.network.retrofit.MissingService::class.java)
    }

    @Provides
    @Singleton
    fun provideReportService(retrofit: Retrofit): com.jnu.network.retrofit.ReportService {
        return retrofit.create(com.jnu.network.retrofit.ReportService::class.java)
    }

    @Provides
    @Singleton
    fun provideWalkingService(retrofit: Retrofit): com.jnu.network.retrofit.WalkingService {
        return retrofit.create(com.jnu.network.retrofit.WalkingService::class.java)
    }

    @Provides
    @Singleton
    fun provideKakaoLocalService(@Named("kakaoLocalRetrofit") retrofit: Retrofit): com.jnu.network.retrofit.KakaoLocalService {
        return retrofit.create(com.jnu.network.retrofit.KakaoLocalService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): com.jnu.network.retrofit.UserService {
        return retrofit.create(com.jnu.network.retrofit.UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
//        selfSigningHelper: SelfSigningHelper
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
//            .sslSocketFactory(
//                selfSigningHelper.sslContext.socketFactory,
//                selfSigningHelper.tmf.trustManagers[0] as X509TrustManager
//            )
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}
