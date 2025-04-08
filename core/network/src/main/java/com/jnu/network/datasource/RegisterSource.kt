package com.jnu.network.datasource

import android.util.Log
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterSource @Inject constructor(
    private val registerService: com.jnu.network.retrofit.RegisterService,
    private val gson: Gson
) {
    suspend fun registerUserAndPet(
        token: String,
        petRegisterDTO: com.jnu.network.model.PetRegisterDTO,
        petImage: File,
        userName: String
    ) {
        val response = registerService.registerUserAndPet(
            token,
            gson.toJson(petRegisterDTO).toRequestBody("application/json".toMediaTypeOrNull()),
            MultipartBody.Part.createFormData(
                "petImage",
                petImage.name,
                petImage.asRequestBody("image/*".toMediaTypeOrNull())
            ),
            userName.toRequestBody("text/plain".toMediaTypeOrNull())
        )
        Log.d("testt", "${response.body().toString()}, ${response.errorBody().toString()}")
        if (!response.isSuccessful) {
            Log.d("testt", " Reg err : ${response.body().toString()}, ${response.errorBody().hashCode()}")
            throw com.jnu.model.APIException(
                gson.fromJson(
                    response.errorBody()?.string(),
                    com.jnu.model.ErrorResponse::class.java
                )
            )
        }
    }
}