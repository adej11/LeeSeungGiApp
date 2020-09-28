package app.android.ad.addev.com.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(
    var movieId: String ,
    var castId: String ,
    var character: String ,
    var name: String ,
    var profile_path: String?
) : Parcelable
