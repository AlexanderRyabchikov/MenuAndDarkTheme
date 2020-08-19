package org.skender.testapp.domain.repository

import io.reactivex.Single
import org.skender.testapp.data.remote.BestsellerRemote
import org.skender.testapp.data.remote.FeedbackRemote
import org.skender.testapp.data.remote.RestaurantRemote
import org.skender.testapp.domain.network.ApiInterface
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: ApiInterface): Repository {
    override fun getListRestaurants(): Single<List<RestaurantRemote>> {
        return api.getListRestaurants().singleOrError()
    }

    override fun getListBestseller(): Single<List<BestsellerRemote>> {
        return api.getListBestSeller().singleOrError()
    }

    override fun getListFeedback(): Single<List<FeedbackRemote>> {
        return api.getListFeedback().singleOrError()
    }
}