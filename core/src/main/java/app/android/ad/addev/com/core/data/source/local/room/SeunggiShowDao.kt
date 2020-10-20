package app.android.ad.addev.com.core.data.source.local.room

import androidx.room.*
import app.android.ad.addev.com.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SeunggiShowDao {

    @Query("SELECT * FROM seunggi_show ")
    fun getAllShows(): Flow<List<SeunggiShowEntity>>

    @Query("SELECT * FROM seunggi_show where isFavorite = 1")
    fun getFavoriteShow(): Flow<List<SeunggiShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(tourism: List<SeunggiShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailShow(castEntity: List<CastEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(albumEntity: List<AlbumEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(trackEntity: List<TrackEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profileEntity: List<ProfileEntity>)

    @Update
    fun updateFavoriteShow(seunggi: SeunggiShowEntity)

    @Query("SELECT * FROM cast_show where movie_id= :movieId ")
    fun getCastShow(movieId: String): Flow<List<CastEntity>>

    @Query("SELECT * FROM album")
    fun getAllAlbums(): Flow<List<AlbumEntity>>

    @Query("SELECT * FROM profile")
    fun getProfie(): Flow<List<ProfileEntity>>

    @Query("SELECT * FROM track where album_id= :albumId ")
    fun getDetailAlbum(albumId: String): Flow<List<TrackEntity>>

    @Query("SELECT * FROM banner")
    fun getAllBanners(): Flow<List<BannerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanner(bannerEntity: List<BannerEntity>)
}
