package app.android.ad.addev.com.lsg.di

import app.android.ad.addev.com.core.data.source.local.room.SeunggiDatabase
import app.android.ad.addev.com.core.data.source.local.room.SeunggiShowDao
import app.android.ad.addev.com.core.domain.repository.ImplSeunggiShowRepository
import app.android.ad.addev.com.core.domain.usecase.SeunggiShowInteractor
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface DfmDependencies {
   // fun provideLeeSeungGiUseCase(): SeunggiShowUseCase
    fun provideRepository(): ImplSeunggiShowRepository
    fun provideInt(): SeunggiShowInteractor
    fun provideDatabase(): SeunggiDatabase
    fun provideSeunggiShowDao(): SeunggiShowDao
}