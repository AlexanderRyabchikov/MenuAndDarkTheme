package org.skender.testapp.data.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bestseller(
    val name: String,
    val image: String?,
    val description: String?
): Parcelable