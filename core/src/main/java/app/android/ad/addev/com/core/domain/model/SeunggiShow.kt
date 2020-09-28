package app.android.ad.addev.com.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeunggiShow(
    val id: String,
    val character: String,
    val name: String,
    val title: String,
    val overview: String,
    val media_type: String,
    val release_date: String,
    val first_air_date: String,
    val genre_ids: String,
    val poster_path: String,
    val vote_count: String,
    val vote_average: String,
    val isFavorite: Boolean
) : Parcelable