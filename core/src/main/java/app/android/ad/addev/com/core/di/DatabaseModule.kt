package app.android.ad.addev.com.core.di

import android.content.Context
import androidx.room.Room
import app.android.ad.addev.com.core.data.source.local.room.SeunggiDatabase
import app.android.ad.addev.com.core.data.source.local.room.SeunggiShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SeunggiDatabase = Room.databaseBuilder(
        context, SeunggiDatabase::class.java, "SeungGi.db"
    ).fallbackToDestructiveMigration().build()


    @Provides
    fun provideSeunggiShowDao(database: SeunggiDatabase): SeunggiShowDao = database.seunggiShowDao()
}