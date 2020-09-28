package app.android.ad.addev.com.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class  CastShowResponse(
    @field:SerializedName("id") val castId: String,
    @field:SerializedName("character") val character: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("profile_path") val profilePath: String?
)