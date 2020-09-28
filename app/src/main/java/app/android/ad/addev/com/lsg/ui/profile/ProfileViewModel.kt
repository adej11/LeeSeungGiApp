package app.android.ad.addev.com.lsg.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import app.android.ad.addev.com.core.domain.usecase.SeunggiShowUseCase

class ProfileViewModel @ViewModelInject constructor(seunggiShowUseCase: SeunggiShowUseCase) : ViewModel() {
    val profile = seunggiShowUseCase.getProfile().asLiveData()
}