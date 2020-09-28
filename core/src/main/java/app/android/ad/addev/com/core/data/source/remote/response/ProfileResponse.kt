package app.android.ad.addev.com.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("hangul") val hangul: String,
    @field:SerializedName("birthdate") val birthdate: String,
    @field:SerializedName("height") val height: String,
    @field:SerializedName("instagram") val instagram: String,
    @field:SerializedName("website") val website: String,
    @field:SerializedName("biography") val biography: String
)