package org.skender.testapp.domain.repository

import io.reactivex.Single
import org.skender.testapp.data.remote.BestsellerRemote
import org.skender.testapp.data.remote.FeedbackRemote
import org.skender.testapp.data.remote.RestaurantRemote

interface Repository {

    fun getListRestaurants(): Single<List<RestaurantRemote>>

    fun getListBestseller(): Single<List<BestsellerRemote>>

    fun getListFeedback(): Single<List<FeedbackRemote>>
}