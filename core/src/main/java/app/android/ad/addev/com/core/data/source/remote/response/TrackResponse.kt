package app.android.ad.addev.com.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class  TrackResponse(
    @field:SerializedName("album_id") val albumId: String,
    @field:SerializedName("song") val song: String
)