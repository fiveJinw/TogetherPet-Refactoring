package com.jnu.network.model

import com.google.gson.annotations.SerializedName

data class ReportResponseDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("image_url") val imageUrl: String,
)