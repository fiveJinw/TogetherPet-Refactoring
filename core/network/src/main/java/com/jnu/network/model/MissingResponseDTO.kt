package com.jnu.network.model

import com.google.gson.annotations.SerializedName

data class MissingResponseDTO(
    @SerializedName("missing_id") val missingId: Long,
    @SerializedName("pet_id") val petId: Long,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("pet_image_url") val petImageUrl: String
)
