package org.skender.testapp.data.remote

import com.google.gson.annotations.SerializedName

class RestaurantRemote (
    @SerializedName("Name")
    val name: String,
    @SerializedName("Logo")
    val logo: String?
)