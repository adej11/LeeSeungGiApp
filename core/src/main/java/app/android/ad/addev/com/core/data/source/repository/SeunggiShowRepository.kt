package app.android.ad.addev.com.core.data.source.repository

import app.android.ad.addev.com.core.data.NetworkBoundResource
import app.android.ad.addev.com.core.data.Resource
import app.android.ad.addev.com.core.data.source.local.LocalDataSource
import app.android.ad.addev.com.core.data.source.remote.RemoteDataSource
import app.android.ad.addev.com.core.data.source.remote.RemoteDataSourceAlbum
import app.android.ad.addev.com.core.data.source.remote.network.ApiResponse
import app.android.ad.addev.com.core.data.source.remote.response.*
import app.android.ad.addev.com.core.domain.model.*
import app.android.ad.addev.com.core.domain.repository.ImplSeunggiShowRepository
import app.android.ad.addev.com.core.utils.AppExecutors
import app.android.ad.addev.com.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeunggiShowRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val remoteDataSourceAlbum: RemoteDataSourceAlbum,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ImplSeunggiShowRepository {

    override fun getAllShows(): Flow<Resource<List<SeunggiShow>>> =
        object : NetworkBoundResource<List<SeunggiShow>, List<SeunggiShowResponse>>() {
            override fun loadFromDB(): Flow<List<SeunggiShow>> {
                return localDataSource.getAllShows().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<SeunggiShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<SeunggiShowResponse>>> =
                remoteDataSource.getAllShows()

            override suspend fun saveCallResult(data: List<SeunggiShowResponse>) {
                val sgList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertShow(sgList)
            }
        }.asFlow()

    override fun getProfile(): Flow<Resource<List<Profile>>> =
        object : NetworkBoundResource<List<Profile>, List<ProfileResponse>>() {
            override fun loadFromDB(): Flow<List<Profile>> {
                return localDataSource.getProfile()
                    .map { DataMapper.mapEntitiesToDomainProfile(it) }
            }

            override fun shouldFetch(data: List<Profile>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ProfileResponse>>> =
                remoteDataSourceAlbum.getProfile()

            override suspend fun saveCallResult(data: List<ProfileResponse>) {
                val profile = DataMapper.mapResponsesToEntitiesProfile(data)
                localDataSource.insertProfile(profile)
            }
        }.asFlow()

    override fun getAllAlbums(): Flow<Resource<List<Album>>> =
        object : NetworkBoundResource<List<Album>, List<AlbumResponse>>() {
            override fun loadFromDB(): Flow<List<Album>> {
                return localDataSource.getAllAlbums()
                    .map { DataMapper.mapEntitiesToDomainAlbum(it) }
            }

            override fun shouldFetch(data: List<Album>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<AlbumResponse>>> =
                remoteDataSourceAlbum.getAllAlbums()

            override suspend fun saveCallResult(data: List<AlbumResponse>) {
                val albumList = DataMapper.mapResponsesToEntitiesAlbum(data)
                localDataSource.insertAlbum(albumList)
            }
        }.asFlow()

    override fun getAllTracks(albumId: String): Flow<Resource<List<Track>>> =
        object : NetworkBoundResource<List<Track>, List<TrackResponse>>() {
            override fun loadFromDB(): Flow<List<Track>> {
                return localDataSource.getAllTrack(albumId)
                    .map { DataMapper.mapEntitiesToDomainTrack(it) }
            }

            override fun shouldFetch(data: List<Track>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TrackResponse>>> =
                remoteDataSourceAlbum.getAllTracks()

            override suspend fun saveCallResult(data: List<TrackResponse>) {
                val trackList = DataMapper.mapResponsesToEntitiesDetail(data)
                localDataSource.insertTrack(trackList)
            }
        }.asFlow()

    override fun getCastShow(movieId: String, mediaType: String): Flow<Resource<List<Cast>>> =
        object : NetworkBoundResource<List<Cast>, List<CastShowResponse>>() {
            override fun loadFromDB(): Flow<List<Cast>> {
                return localDataSource.getCastShow(movieId).map {
                    DataMapper.mapEntitiesToDomainDetail(it, movieId)
                }
            }

            override fun shouldFetch(data: List<Cast>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<CastShowResponse>>> =
                remoteDataSource.getCastShow(movieId, mediaType)

            override suspend fun saveCallResult(data: List<CastShowResponse>) {
                val sgList = DataMapper.mapDetailResponsesToEntities(data, movieId)
                localDataSource.insertDetailShow(sgList)
            }
        }.asFlow()

    override fun getFavoriteShow(): Flow<List<SeunggiShow>> {
        return localDataSource.getFavoriteShow().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteShow(sg: SeunggiShow, state: Boolean) {
        val seungiShowEntity = DataMapper.mapDomainToEntity(sg)
        appExecutors.diskIO().execute { localDataSource.setFavoriteShow(seungiShowEntity, state) }
    }
}

