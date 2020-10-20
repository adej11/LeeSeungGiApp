package app.android.ad.addev.com.lsg.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import app.android.ad.addev.com.core.domain.usecase.SeunggiShowUseCase

class BannerViewModel @ViewModelInject constructor(seunggiShowUseCase: SeunggiShowUseCase) :
    ViewModel() {
    val banners = seunggiShowUseCase.getBanner().asLiveData()
}