package app.android.ad.addev.com.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Credits(
    @field:SerializedName("cast")
    val cast: List<CastShowResponse>
)