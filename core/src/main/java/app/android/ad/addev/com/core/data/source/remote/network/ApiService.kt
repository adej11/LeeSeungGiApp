package app.android.ad.addev.com.core.data.source.remote.network

import app.android.ad.addev.com.core.data.source.remote.response.ListShowResponse
import app.android.ad.addev.com.core.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("person/1250842/combined_credits?api_key=94923667dddfb4d9c55abf3d89a018ac&query=lee+seung+gi")
    suspend fun getList(): ListShowResponse

    @GET("{media_type}/{id}?append_to_response=credits")
    suspend fun getCastShow(
        @Path("id") id: String, @Path("media_type") mediaType: String, @Query("api_key") apiKey: String?= Constant.APP_KEY,
        @Query("language") language: String?
    ): ListShowResponse
}