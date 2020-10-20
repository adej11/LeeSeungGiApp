package app.android.ad.addev.com.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "seunggi_show")
data class SeunggiShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String = "",
    @ColumnInfo(name = "character")
    var character: String = "",
    @ColumnInfo(name = "title")
    var title: String? = "",
    @ColumnInfo(name = "name")
    var name: String? = "",
    @ColumnInfo(name = "overview")
    var overview: String = "",
    @ColumnInfo(name = "media_type")
    var media_type: String = "",
    @ColumnInfo(name = "release_date")
    var release_date: String? = "",
    @ColumnInfo(name = "first_air_date")
    var first_air_date: String? = "",
    @ColumnInfo(name = "genre_ids")
    var genre_ids: String? = "",
    @ColumnInfo(name = "poster_path")
    var poster_path: String? = "",
    @ColumnInfo(name = "vote_count")
    var voteCount: String? = "",
    @ColumnInfo(name = "vote_average")
    var voteAverage: String? = "",
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
    ) : Parcelable
