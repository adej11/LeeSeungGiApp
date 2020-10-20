package app.android.ad.addev.com.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "track")
data class TrackEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "album_id")
    var album_id: String = "",
    @ColumnInfo(name = "song")
    var song: String = ""
)
