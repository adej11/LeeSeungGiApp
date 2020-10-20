package app.android.ad.addev.com.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import app.android.ad.addev.com.core.data.source.local.entity.*

@Database(
    entities = [SeunggiShowEntity::class, CastEntity::class, AlbumEntity::class, TrackEntity::class, ProfileEntity::class, BannerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SeunggiDatabase : RoomDatabase() {
    abstract fun seunggiShowDao(): SeunggiShowDao
}