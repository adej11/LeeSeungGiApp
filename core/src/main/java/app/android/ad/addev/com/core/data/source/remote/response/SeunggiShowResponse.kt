package app.android.ad.addev.com.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SeunggiShowResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("character")
    val character: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("media_type")
    val media_type: String,
    @field:SerializedName("release_date")
    val release_date: String,
    @field:SerializedName("first_air_date")
    val first_air_date: String,
    @field:SerializedName("original_language")
    val genre_ids: String,
    @field:SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("vote_count")
    var voteCount: String? = null,
    @SerializedName("vote_average")
    var voteAverage: String? = null,
)

