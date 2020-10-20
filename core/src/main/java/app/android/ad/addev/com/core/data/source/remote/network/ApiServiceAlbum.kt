package app.android.ad.addev.com.core.data.source.remote.network

import app.android.ad.addev.com.core.data.source.remote.response.ListShowResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceAlbum {
    @GET("{path_url}")
    suspend fun getData(@Path("path_url") path_url: String
    ): ListShowResponse
}