package app.android.ad.addev.com.core.domain.repository

import app.android.ad.addev.com.core.data.Resource
import app.android.ad.addev.com.core.domain.model.*
import kotlinx.coroutines.flow.Flow

interface ImplSeunggiShowRepository {
    fun getAllShows(): Flow<Resource<List<SeunggiShow>>>
    fun getAllAlbums(): Flow<Resource<List<Album>>>
    fun getProfile(): Flow<Resource<List<Profile>>>
    fun getAllTracks(albumId: String): Flow<Resource<List<Track>>>
    fun getCastShow(movieId: String, mediaType: String): Flow<Resource<List<Cast>>>
    fun getFavoriteShow(): Flow<List<SeunggiShow>>
    fun getAllBanners(): Flow<Resource<List<SliderItem>>>
    fun setFavoriteShow(seunggi: SeunggiShow, state: Boolean)
}