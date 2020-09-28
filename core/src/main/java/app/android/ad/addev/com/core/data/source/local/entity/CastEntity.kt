package app.android.ad.addev.com.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cast_show")
data class CastEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "movie_id")
    var movieId: String = "",
    @ColumnInfo(name = "cast_id")
    var castId: String = "",
    @ColumnInfo(name = "character")
    var character: String = "",
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "profile_path")
    var profilePath: String? = ""
) : Parcelable
