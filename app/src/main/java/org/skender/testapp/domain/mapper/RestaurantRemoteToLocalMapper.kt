package org.skender.testapp.domain.mapper

import org.skender.testapp.data.local.Restaurant
import org.skender.testapp.data.remote.RestaurantRemote
import javax.inject.Inject

class RestaurantRemoteToLocalMapper @Inject constructor(): AbstractMapper<RestaurantRemote, Restaurant>() {
    override fun map(value: RestaurantRemote): Restaurant {
        return Restaurant(
            value.name,
            value.logo
        )
    }
}