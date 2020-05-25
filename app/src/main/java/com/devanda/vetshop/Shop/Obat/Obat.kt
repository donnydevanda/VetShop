package com.devanda.vetshop.Shop.Obat

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Obat (
    var nama: String ?="",
    var harga: String ?="",
    var deskripsi: String ?="",
    var images: String ?=""
): Parcelable