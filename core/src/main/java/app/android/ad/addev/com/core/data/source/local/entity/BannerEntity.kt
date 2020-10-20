package app.android.ad.addev.com.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "banner", indices = [Index(value = arrayOf("url_image"), unique = true)])
data class BannerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "url_image")
    var urlImage: String = ""
)
