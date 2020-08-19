package org.skender.testapp.data.remote

import com.google.gson.annotations.SerializedName

class BestsellerRemote(
    @SerializedName("ProductName")
    val name: String,
    @SerializedName("ProductImage")
    val image: String?,
    @SerializedName("ProductDescription")
    val description: String?
)