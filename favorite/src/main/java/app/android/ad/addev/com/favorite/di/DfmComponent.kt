package app.android.ad.addev.com.favorite.di

import android.content.Context
import app.android.ad.addev.com.favorite.favorite.FavoriteActivity
import app.android.ad.addev.com.lsg.di.AppModule
import app.android.ad.addev.com.lsg.di.AppScope
import app.android.ad.addev.com.lsg.di.DfmDependencies
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [AppModule::class],
    dependencies = [DfmDependencies::class]
)
interface DfmComponent {

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(dependencies: DfmDependencies): Builder
        fun build(): DfmComponent
    }

    fun inject(activity: FavoriteActivity)
}
