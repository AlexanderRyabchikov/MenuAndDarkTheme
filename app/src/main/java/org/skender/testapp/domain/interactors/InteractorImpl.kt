package org.skender.testapp.domain.interactors

import io.reactivex.Single
import org.skender.testapp.data.remote.BestsellerRemote
import org.skender.testapp.data.remote.FeedbackRemote
import org.skender.testapp.data.remote.RestaurantRemote
import org.skender.testapp.domain.repository.Repository
import javax.inject.Inject

class InteractorImpl @Inject constructor(private val repository: Repository): Interactor {
    override fun getListRestaurants(): Single<List<RestaurantRemote>> {
        return repository.getListRestaurants()
    }

    override fun getListBestseller(): Single<List<BestsellerRemote>> {
        return repository.getListBestseller()
    }

    override fun getListFeedback(): Single<List<FeedbackRemote>> {
        return repository.getListFeedback()
    }


}