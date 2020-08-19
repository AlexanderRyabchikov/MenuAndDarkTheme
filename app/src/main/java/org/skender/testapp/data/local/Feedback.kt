package org.skender.testapp.data.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Feedback (
    val isPositive: Boolean,
    val message: String,
    val userName: String,
    val date: String,
    val restaurantName: String
): Parcelable