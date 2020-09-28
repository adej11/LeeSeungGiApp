package app.android.ad.addev.com.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "album")
data class AlbumEntity(
    @PrimaryKey   @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "release_date")
    var release_date: String = "",
    @ColumnInfo(name = "image")
    var image: String = "",
) : Parcelable
