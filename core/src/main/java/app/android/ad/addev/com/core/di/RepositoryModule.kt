package app.android.ad.addev.com.core.di

import app.android.ad.addev.com.core.data.source.repository.SeunggiShowRepository
import app.android.ad.addev.com.core.domain.repository.ImplSeunggiShowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
     @Binds
    abstract fun provideRepository(seunggiShowRepository: SeunggiShowRepository): ImplSeunggiShowRepository
}