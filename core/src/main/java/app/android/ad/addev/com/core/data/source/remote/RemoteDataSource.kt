package app.android.ad.addev.com.core.data.source.remote

import android.util.Log
import app.android.ad.addev.com.core.data.source.remote.network.ApiResponse
import app.android.ad.addev.com.core.data.source.remote.network.ApiService
import app.android.ad.addev.com.core.data.source.remote.response.CastShowResponse
import app.android.ad.addev.com.core.data.source.remote.response.SeunggiShowResponse
import app.android.ad.addev.com.core.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getAllShows(): Flow<ApiResponse<List<SeunggiShowResponse>>> {

        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.cast
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.cast))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    fun getCastShow(id:String,mediaType:String): Flow<ApiResponse<List<CastShowResponse>>> {

        return flow {
            try {
                val response = apiService.getCastShow(id,mediaType, Constant.APP_KEY,"id")
                val dataArray = response.credits.cast
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

