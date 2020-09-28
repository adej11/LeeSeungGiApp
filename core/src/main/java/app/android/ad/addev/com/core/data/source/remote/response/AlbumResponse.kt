package app.android.ad.addev.com.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("release_date") val release_date: String,
    @field:SerializedName("image") val image: String?
)