package org.skender.testapp.domain.network

import io.reactivex.Observable
import org.skender.testapp.data.remote.BestsellerRemote
import org.skender.testapp.data.remote.FeedbackRemote
import org.skender.testapp.data.remote.RestaurantRemote
import retrofit2.http.GET

interface ApiInterface {
    companion object {
        private const val GET_LIST_RESTAURANTS = "restaurants"
        private const val GET_LIST_BESTSELLER = "hits"
        private const val GET_LIST_FEEDBACK = "reviews"
    }

    @GET(GET_LIST_RESTAURANTS)
    fun getListRestaurants(): Observable<List<RestaurantRemote>>

    @GET(GET_LIST_BESTSELLER)
    fun getListBestSeller(): Observable<List<BestsellerRemote>>

    @GET(GET_LIST_FEEDBACK)
    fun getListFeedback(): Observable<List<FeedbackRemote>>
}