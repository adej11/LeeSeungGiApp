package app.android.ad.addev.com.core.data.source.local

import app.android.ad.addev.com.core.data.source.local.entity.*
import app.android.ad.addev.com.core.data.source.local.room.SeunggiShowDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val seunggiShowDao: SeunggiShowDao) {

    fun getAllShows(): Flow<List<SeunggiShowEntity>> = seunggiShowDao.getAllShows()

    fun getCastShow(movieId: String): Flow<List<CastEntity>> = seunggiShowDao.getCastShow(movieId)

    fun getFavoriteShow(): Flow<List<SeunggiShowEntity>> = seunggiShowDao.getFavoriteShow()

    suspend fun insertShow(showList: List<SeunggiShowEntity>) =
        seunggiShowDao.insertShow(showList)

    suspend fun insertProfile(profile: List<ProfileEntity>) =
        seunggiShowDao.insertProfile(profile)

    suspend fun insertAlbum(albumList: List<AlbumEntity>) =
        seunggiShowDao.insertAlbum(albumList)

    suspend fun insertBanner(bannerList: List<BannerEntity>) =
        seunggiShowDao.insertBanner(bannerList)

    suspend fun insertDetailShow(show: List<CastEntity>) =
        seunggiShowDao.insertDetailShow(show)

    suspend fun insertTrack(track: List<TrackEntity>) =
        seunggiShowDao.insertTrack(track)

    fun setFavoriteShow(seunggi: SeunggiShowEntity, newState: Boolean) {
        seunggi.isFavorite = newState
        seunggiShowDao.updateFavoriteShow(seunggi)
    }

    fun getAllAlbums(): Flow<List<AlbumEntity>> = seunggiShowDao.getAllAlbums()

    fun getAllTrack(albumId: String): Flow<List<TrackEntity>> =
        seunggiShowDao.getDetailAlbum(albumId)

    fun getProfile(): Flow<List<ProfileEntity>> = seunggiShowDao.getProfie()

    fun getAllBanners(): Flow<List<BannerEntity>> = seunggiShowDao.getAllBanners()

}