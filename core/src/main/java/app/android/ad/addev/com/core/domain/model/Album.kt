package app.android.ad.addev.com.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    var id: String ,
    var name: String ,
    var releaseDate: String ,
    var image: String
) : Parcelable
