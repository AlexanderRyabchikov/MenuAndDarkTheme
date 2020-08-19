package org.skender.testapp.data.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant (
    val name: String,
    val logo: String?
): Parcelable