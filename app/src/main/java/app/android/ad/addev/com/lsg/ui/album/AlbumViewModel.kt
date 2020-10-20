package app.android.ad.addev.com.lsg.ui.album

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import app.android.ad.addev.com.core.data.Resource
import app.android.ad.addev.com.core.domain.model.Track
import app.android.ad.addev.com.core.domain.usecase.SeunggiShowUseCase

class AlbumViewModel @ViewModelInject constructor(private val seunggiShowUseCase: SeunggiShowUseCase) : ViewModel() {
    val albums = seunggiShowUseCase.getAllAlbums().asLiveData()
     var tracks: LiveData<Resource<List<Track>>>? = null
    fun init(id: String ) {
        tracks = seunggiShowUseCase.getAllTracks(id).asLiveData()
    }
}