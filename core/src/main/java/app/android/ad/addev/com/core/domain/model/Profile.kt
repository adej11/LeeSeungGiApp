package app.android.ad.addev.com.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    var name: String ,
    var hangul: String ,
    var birthdate: String,
    var height: String,
    var instagram: String,
    var website: String,
    var biography: String
) : Parcelable
