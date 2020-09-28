package app.android.ad.addev.com.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import app.android.ad.addev.com.core.domain.usecase.SeunggiShowUseCase
import app.android.ad.addev.com.lsg.di.AppScope
import javax.inject.Inject
@AppScope
class FavoriteViewModel @Inject constructor  (seunggiShowUseCase: SeunggiShowUseCase) : ViewModel() {
    val seunggiShow = seunggiShowUseCase.getFavoriteShow().asLiveData()
}