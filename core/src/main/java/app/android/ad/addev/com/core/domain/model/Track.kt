package app.android.ad.addev.com.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(
    var albumId: String ,
    var song: String
) : Parcelable
