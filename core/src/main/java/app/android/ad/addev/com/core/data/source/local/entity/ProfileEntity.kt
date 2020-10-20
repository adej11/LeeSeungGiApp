package app.android.ad.addev.com.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "hangul")
    var hangul: String = "",
    @ColumnInfo(name = "birthdate")
    var birthdate: String = "",
    @ColumnInfo(name = "height")
    var height: String = "", @ColumnInfo(name = "instagram")
    var instagram: String = "",
    @ColumnInfo(name = "website")
    var website: String = "",
    @ColumnInfo(name = "biography")
    var biography: String = ""
)
