package com.devanda.vetshop.Shop.Makanan

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Makanan (
    var nama: String ?="",
    var harga: String ?="",
    var deskripsi: String ?="",
    var images: String ?=""
): Parcelable