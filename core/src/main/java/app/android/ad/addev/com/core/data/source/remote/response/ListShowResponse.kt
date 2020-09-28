package app.android.ad.addev.com.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListShowResponse(
    @field:SerializedName("cast")
    val cast: List<SeunggiShowResponse>,
    @field:SerializedName("credits")
    val credits: Credits,
    @field:SerializedName("albums")
    val album: List<AlbumResponse>,
    @field:SerializedName("track")
    val listTrack: List<TrackResponse>,
    @field:SerializedName("profile")
    val profile: List<ProfileResponse>
)