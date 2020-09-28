package app.android.ad.addev.com.lsg.di

import app.android.ad.addev.com.core.domain.usecase.SeunggiShowInteractor
import app.android.ad.addev.com.core.domain.usecase.SeunggiShowUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideLeeSeungGiUseCase(seunggiShowInteractor: SeunggiShowInteractor): SeunggiShowUseCase
}