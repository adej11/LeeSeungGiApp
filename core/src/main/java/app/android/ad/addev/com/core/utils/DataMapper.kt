package app.android.ad.addev.com.core.utils

import app.android.ad.addev.com.core.data.source.local.entity.*
import app.android.ad.addev.com.core.data.source.remote.response.*
import app.android.ad.addev.com.core.domain.model.*

object DataMapper {
    fun mapResponsesToEntities(input: List<SeunggiShowResponse>): List<SeunggiShowEntity> {
        val sgList = ArrayList<SeunggiShowEntity>()
        input.map {
            val seunggiShow = SeunggiShowEntity(
                id = it.id,
                character = it.character,
                title = it.title,
                name = it.name,
                overview = it.overview,
                media_type = it.media_type,
                release_date = it.release_date,
                first_air_date = it.first_air_date,
                genre_ids = it.genre_ids,
                poster_path = it.poster_path,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                isFavorite = false
            )
            sgList.add(seunggiShow)
        }

        return sgList
    }

    fun mapResponsesToEntitiesAlbum(input: List<AlbumResponse>): List<AlbumEntity> {
        val albumList = ArrayList<AlbumEntity>()
        input.map {
            val albumEntity = AlbumEntity(
                id = it.id,
                name = it.name,
                release_date = it.release_date,
                image = it.image.toString(),
            )
            albumList.add(albumEntity)
        }
        return albumList
    }

    fun mapResponsesToEntitiesProfile(input: List<ProfileResponse>): List<ProfileEntity> {
        val profiles = ArrayList<ProfileEntity>()
        input.map {
            val profile = ProfileEntity(
                id = 0,
                name = it.name,
                hangul = it.hangul,
                birthdate = it.birthdate,
                height = it.height,
                instagram = it.instagram,
                website = it.website,
                biography = it.biography
            )
            profiles.add(profile)
        }
        return profiles
    }

    fun mapResponsesToEntitiesDetail(input: List<TrackResponse>): List<TrackEntity> {
        val trackList = ArrayList<TrackEntity>()
        input.map {
            val trackEntity = TrackEntity(
                id = 0,
                album_id = it.albumId,
                song = it.song
            )
            trackList.add(trackEntity)
        }

        return trackList
    }

    fun mapEntitiesToDomain(input: List<SeunggiShowEntity>): List<SeunggiShow> =
        input.map {
            SeunggiShow(
                id = it.id,
                character = it.character,
                title = it.title.toString(),
                name = it.name.toString(),
                overview = it.overview,
                media_type = it.media_type,
                release_date = it.release_date.toString(),
                first_air_date = it.first_air_date.toString(),
                genre_ids = it.genre_ids.toString(),
                poster_path = it.poster_path.toString(),
                vote_count = it.voteCount.toString(),
                vote_average = it.voteAverage.toString(),
                isFavorite = it.isFavorite
            )
        }

    fun mapEntitiesToDomainProfile(input: List<ProfileEntity>): List<Profile> =
        input.map {
            Profile(
                name = it.name,
                hangul = it.hangul,
                birthdate = it.birthdate,
                height = it.height,
                instagram = it.instagram,
                website = it.website,
                biography = it.biography
            )
        }

    fun mapEntitiesToDomainAlbum(input: List<AlbumEntity>): List<Album> =
        input.map {
            Album(
                id = it.id,
                name = it.name,
                releaseDate = it.release_date,
                image = it.image
            )
        }

    fun mapEntitiesToDomainBanner(input: List<BannerEntity>): List<SliderItem> =
        input.map {
            SliderItem(
                image = it.urlImage
            )
        }

    fun mapEntitiesToDomainTrack(input: List<TrackEntity>): List<Track> =
        input.map {
            Track(
                albumId = it.album_id,
                song = it.song
            )
        }

    fun mapDomainToEntity(it: SeunggiShow) = SeunggiShowEntity(
        id = it.id,
        character = it.character,
        title = it.title,
        name = it.name,
        overview = it.overview,
        media_type = it.media_type,
        release_date = it.release_date,
        first_air_date = it.first_air_date,
        genre_ids = it.genre_ids,
        poster_path = it.poster_path,
        voteCount = it.vote_count,
        voteAverage = it.vote_average,
        isFavorite = it.isFavorite
    )

    fun mapEntitiesToDomainDetail(input: List<CastEntity>, movieId: String): List<Cast> =
        input.map {
            Cast(
                movieId = movieId,
                castId = it.castId,
                character = it.character,
                name = it.name,
                profile_path = it.profilePath.toString(),
            )
        }

    fun mapDetailResponsesToEntities(
        input: List<CastShowResponse>,
        movieId: String
    ): List<CastEntity> {
        val castList = ArrayList<CastEntity>()
        input.map {
            val castShow = CastEntity(
                id = 0,
                movieId = movieId,
                castId = it.castId,
                character = it.character,
                name = it.name,
                profilePath = it.profilePath.toString()
            )
            castList.add(castShow)
        }
        return castList
    }

    fun mapResponsesToEntitiesBanner(input: List<BannerResponse>): List<BannerEntity> {
        val albumList = ArrayList<BannerEntity>()
        input.map {
            val bannerEntity = BannerEntity(
                0,
                urlImage = it.urlImage
            )
            albumList.add(bannerEntity)
        }
        return albumList
    }
}