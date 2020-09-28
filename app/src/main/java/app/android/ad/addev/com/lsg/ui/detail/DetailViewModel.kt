package app.android.ad.addev.com.lsg.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import app.android.ad.addev.com.core.data.Resource
import app.android.ad.addev.com.core.domain.model.Cast
import app.android.ad.addev.com.core.domain.model.SeunggiShow
import app.android.ad.addev.com.core.domain.usecase.SeunggiShowUseCase

class DetailViewModel @ViewModelInject constructor(private val seunggiShowUseCase: SeunggiShowUseCase) :
    ViewModel() {
    var cast: LiveData<Resource<List<Cast>>>? = null
    fun init(id: String, mediaType: String) {
        cast = seunggiShowUseCase.getCastShow(id, mediaType).asLiveData()
    }

    fun setFavoriteShow(show: SeunggiShow, newStatus: Boolean) =
        seunggiShowUseCase.setFavoriteShow(show, newStatus)
}