package com.devanda.vetshop.Shop.Rumah

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rumah (
    var nama: String ?="",
    var harga: String ?="",
    var deskripsi: String ?="",
    var images: String ?=""
): Parcelable