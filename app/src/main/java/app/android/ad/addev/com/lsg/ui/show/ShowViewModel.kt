package app.android.ad.addev.com.lsg.ui.show

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import app.android.ad.addev.com.core.domain.usecase.SeunggiShowUseCase

class ShowViewModel @ViewModelInject constructor(seunggiShowUseCase: SeunggiShowUseCase) : ViewModel() {
    val seunggiShow = seunggiShowUseCase.getAllShows().asLiveData()
}