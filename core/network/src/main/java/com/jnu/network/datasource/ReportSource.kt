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
class ReportSource @Inject constructor(
    private val reportService: com.jnu.network.retrofit.ReportService,
    private val gson: Gson
) {
    suspend fun registerReport(
        token: String,
        reportCreateRequestDTO: com.jnu.network.model.ReportCreateRequestDTO,
        files: List<File>
    ) {
        Log.d("sendReport", "File List in ReportSource: $files")
        val fileParts = files.map { file ->
            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            val part = MultipartBody.Part.createFormData(
                "files",
                file.name,
                requestBody
            )

            Log.d("sendReport", "Multipart Part - File Name: ${file.name}, File Size: ${file.length()} bytes")

            part
        }


        val response = reportService.registerReportByMissing(
            token,
            gson.toJson(reportCreateRequestDTO)
                .toRequestBody("application/json".toMediaTypeOrNull()),
            *fileParts.toTypedArray() // 변환한 파트를 배열로 전달
        )


        if (!response.isSuccessful) {
            val e = com.jnu.model.APIException(
                gson.fromJson(
                    response.errorBody()?.string(),
                    com.jnu.model.ErrorResponse::class.java
                )
            )
            throw e
        }
    }

    suspend fun getRegisterOwnByUser(
        token: String
    ): List<com.jnu.network.model.ReportResponseDTO> {
        Log.d("sendReport","getRegisterOwnByUser()")
        val response = reportService.getReportOwnByUser(token)

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

    suspend fun getReportByLocation(
        latitude: Double,
        longitude: Double
    ): List<com.jnu.network.model.ReportResponseDTO> {
        Log.d("sendReport","getReportByLocation()")
        val response = reportService.getReportByLocation(latitude, longitude)
        Log.d("MissingSource", "JSON Response: $response")

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

    suspend fun getReportDetail(
        reportId: Number
    ): com.jnu.network.model.ReportDetailResponseDTO {
        val response = reportService.getReportDetail(reportId)

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
}