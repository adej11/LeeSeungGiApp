package app.android.ad.addev.com.core.di

import app.android.ad.addev.com.core.data.source.remote.network.ApiService
import app.android.ad.addev.com.core.data.source.remote.network.ApiServiceAlbum
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build()
    }

    @Provides
    fun provideApiServie(client: OkHttpClient): ApiService {
        val retrofit= Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    fun provideApiServiceAlbum(client: OkHttpClient): ApiServiceAlbum {
        val retrofit= Retrofit.Builder()
            .baseUrl("https://addevstudio.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
        return retrofit.create(ApiServiceAlbum::class.java)
    }
}