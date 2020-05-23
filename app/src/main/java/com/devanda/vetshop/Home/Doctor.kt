package com.devanda.vetshop.Home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Doctor (
    var nama: String ?="",
    var alamat: String ?="",
    var praktek: String ?="",
    var info: String ?="",
    var images: String ?="",
    var telefon: String ?=""
): Parcelable