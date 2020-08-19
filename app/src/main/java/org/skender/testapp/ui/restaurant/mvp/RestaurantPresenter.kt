package org.skender.testapp.ui.restaurant.mvp

import moxy.InjectViewState
import org.skender.testapp.data.local.Restaurant
import org.skender.testapp.domain.interactors.Interactor
import org.skender.testapp.domain.mapper.RestaurantRemoteToLocalMapper
import org.skender.testapp.ui.base.AbstractPresenter
import org.skender.testapp.utils.asyncSingle
import java.util.*
import javax.inject.Inject

@InjectViewState
class RestaurantPresenter @Inject constructor(private val interactor: Interactor,
                                              private val mapper: RestaurantRemoteToLocalMapper
): AbstractPresenter<RestaurantContract>(), RestaurantContract.Presenter {

    private lateinit var listRestaurants: List<Restaurant>

    override fun onDataRequest() {

        subscribe(
            interactor
                .getListRestaurants()
                .compose(asyncSingle())
                .doOnSubscribe { viewState.showLoading() }
                .doOnSuccess { viewState.showData() }
                .map(mapper::map)
                .subscribe({
                    if (it != null) {
                        listRestaurants = it
                        viewState.showList(listRestaurants)
                    }
                }, this::handleError)
        )

    }

    override fun search(string: String) {
        val data = ArrayList<Restaurant>()
        for (item in listRestaurants) {
            if (item.name.toLowerCase(Locale.getDefault()).contains(string.toLowerCase(Locale.getDefault()))) {
                data.add(item)
            }
        }

        viewState.showList(data)
    }
}