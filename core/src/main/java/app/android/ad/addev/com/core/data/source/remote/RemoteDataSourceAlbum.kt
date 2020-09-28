package app.android.ad.addev.com.core.data.source.remote

import android.util.Log
import app.android.ad.addev.com.core.data.source.remote.network.ApiResponse
import app.android.ad.addev.com.core.data.source.remote.network.ApiServiceAlbum
import app.android.ad.addev.com.core.data.source.remote.response.AlbumResponse
import app.android.ad.addev.com.core.data.source.remote.response.ProfileResponse
import app.android.ad.addev.com.core.data.source.remote.response.TrackResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceAlbum @Inject constructor(private val apiServiceAlbum: ApiServiceAlbum) {

    fun getAllAlbums(): Flow<ApiResponse<List<AlbumResponse>>> {
        return flow {
            try {
                val response = apiServiceAlbum.getData("album")
                val dataArray = response.album
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.album))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    fun getAllTracks(): Flow<ApiResponse<List<TrackResponse>>> {
        return flow {
            try {
                val response = apiServiceAlbum.getData("track")
                val dataArray = response.listTrack
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    fun getProfile(): Flow<ApiResponse<List<ProfileResponse>>> {
        return flow {
            try {
                val response = apiServiceAlbum.getData("profile")
                val dataArray = response.profile
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

