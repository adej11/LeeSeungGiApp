package app.android.ad.addev.com.core.domain.usecase

import app.android.ad.addev.com.core.domain.model.SeunggiShow
import app.android.ad.addev.com.core.domain.repository.ImplSeunggiShowRepository
import javax.inject.Inject

class SeunggiShowInteractor @Inject constructor(private val seunggiShowRepository: ImplSeunggiShowRepository):
    SeunggiShowUseCase {
    override fun getAllShows() = seunggiShowRepository.getAllShows()
    override fun getAllAlbums() = seunggiShowRepository.getAllAlbums()
    override fun getProfile() = seunggiShowRepository.getProfile()
    override fun getAllTracks(albumId:String) = seunggiShowRepository.getAllTracks(albumId)
    override fun getCastShow(movieId:String,mediaType:String )=  seunggiShowRepository.getCastShow(movieId,mediaType)
    override fun getFavoriteShow() = seunggiShowRepository.getFavoriteShow()
    override fun getBanner() = seunggiShowRepository.getAllBanners()
    override fun setFavoriteShow(seunggi: SeunggiShow, state: Boolean) =
        seunggiShowRepository.setFavoriteShow(seunggi, state)
}