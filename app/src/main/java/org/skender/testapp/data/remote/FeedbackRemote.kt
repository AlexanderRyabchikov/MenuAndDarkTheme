package org.skender.testapp.data.remote

import com.google.gson.annotations.SerializedName

class FeedbackRemote(
    @SerializedName("IsPositive")
    val isPositive: Boolean,
    @SerializedName("Message")
    val message: String,
    @SerializedName("UserFIO")
    val userName: String,
    @SerializedName("DateAdded")
    val date: String,
    @SerializedName("RestaurantName")
    val restaurantName: String
)