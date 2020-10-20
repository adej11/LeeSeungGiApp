package app.android.ad.addev.com.core.di

import app.android.ad.addev.com.core.data.source.remote.network.ApiService
import app.android.ad.addev.com.core.data.source.remote.network.ApiServiceAlbum
import app.android.ad.addev.com.core.utils.Constant.Companion.DOMAIN_LSG
import app.android.ad.addev.com.core.utils.Constant.Companion.DOMAIN_MOVIE
import app.android.ad.addev.com.core.utils.Constant.Companion.SHA_LSG
import app.android.ad.addev.com.core.utils.Constant.Companion.SHA_MOVIE
import app.android.ad.addev.com.core.utils.Constant.Companion.URL_LSG
import app.android.ad.addev.com.core.utils.Constant.Companion.URL_MOVIE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.CertificatePinner
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
        val certificatePinner = CertificatePinner.Builder()
            .add(DOMAIN_MOVIE, SHA_MOVIE)
            .add(DOMAIN_LSG, SHA_LSG)
            .build()
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Provides
    fun provideApiServie(client: OkHttpClient): ApiService {
        val retrofit= Retrofit.Builder()
            .baseUrl(URL_MOVIE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    fun provideApiServiceAlbum(client: OkHttpClient): ApiServiceAlbum {
        val retrofit= Retrofit.Builder()
            .baseUrl(URL_LSG)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
        return retrofit.create(ApiServiceAlbum::class.java)
    }
}