package com.devanda.vetshop.Shop.Mainan

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Mainan (
    var nama: String ?="",
    var harga: String ?="",
    var deskripsi: String ?="",
    var images: String ?=""
): Parcelable